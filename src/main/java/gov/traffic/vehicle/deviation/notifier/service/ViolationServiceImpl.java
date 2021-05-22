package gov.traffic.vehicle.deviation.notifier.service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.traffic.vehicle.deviation.notifier.exception.ApiErrors;
import gov.traffic.vehicle.deviation.notifier.exception.VehicleApiException;
import gov.traffic.vehicle.deviation.notifier.model.VehicleDeviationNotificationMessage;
import gov.traffic.vehicle.deviation.notifier.model.VehicleViolationInfo;
import gov.traffic.vehicle.deviation.notifier.model.ViolationDto;
import gov.traffic.vehicle.deviation.notifier.repository.VehicleRepository;
import gov.traffic.vehicle.deviation.notifier.repository.ViolationRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.ViolationsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Value("${aws.sns.arn}")
    private String targetTopicArn;


    @Override
    public Mono<String> addVoilations(ViolationDto violationsList, String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .flatMap(vehicle -> violationRepository.save(violationsToBeSaved(violationsList, vehicle.getVehicleNumber())))
                .map(ViolationsEntity::getVehicleNumber)
                .switchIfEmpty(Mono.error(() -> new VehicleApiException(ApiErrors.NOT_FOUND, "Vehicle not found with given number.")));
    }

    @Override
    public Mono<VehicleViolationInfo> getViolations(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .flatMap(vehicleEntity -> violationRepository.findAllByVehicleNumber(vehicleNumber)
                        .collectList()
                        .map(this::convertToViolationsDtoList)
                        .map(violationDtos -> getVehicleViolationInfo(vehicleEntity, violationDtos))
                )
                .switchIfEmpty(Mono.error(() -> new VehicleApiException(ApiErrors.NOT_FOUND, "Vehicle not found with given number.")));

    }

    @Override
    public void notifyViolations(String vehicleNumber, String location) {

        getViolations(vehicleNumber).map(vehicleViolationInfo -> {
            List<ViolationDto> violationDtoList = vehicleViolationInfo.getViolations();
            VehicleDeviationNotificationMessage notificationMessage = new VehicleDeviationNotificationMessage(vehicleNumber, location);
            violationDtoList.sort(Comparator.comparing(ViolationDto::getViolationDate));
            if (!violationDtoList.isEmpty()) {
                if (vehicleViolationInfo.getTotalFine() > 10000 || Instant.now().isAfter(violationDtoList.get(0).getViolationDate().plus(Duration.ofDays(30)))) {
                    amazonSNSClient.publish(new PublishRequest(targetTopicArn, objectToString(notificationMessage), "seize vehicle!"));
                    log.info("published message for vehicle {}", vehicleViolationInfo.getVehicleNumber());
                }
            }
            return Mono.empty();
        }).subscribe();


    }

    private VehicleViolationInfo getVehicleViolationInfo(gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity vehicleEntity, List<ViolationDto> violationDtos) {
        return new VehicleViolationInfo(vehicleEntity.getVehicleNumber(), vehicleEntity.getVehicleName(), vehicleEntity.getOwnerAdharId(), vehicleEntity.getOwnerName(), totalFineAmount(violationDtos), violationDtos);
    }

    private Double totalFineAmount(List<ViolationDto> violationDtos) {
        return violationDtos.stream().map(ViolationDto::getFineAmount).mapToDouble(Double::doubleValue).sum();
    }

    private List<ViolationDto> convertToViolationsDtoList(List<ViolationsEntity> violationsEntityList) {

        return violationsEntityList.stream().map(this::convertToViolationsDto).collect(Collectors.toList());
    }

    private ViolationDto convertToViolationsDto(ViolationsEntity violationsEntity) {
        return new ViolationDto(violationsEntity.getType(), violationsEntity.getFineAmount(), violationsEntity.getViolationDate());
    }

    private ViolationsEntity violationsToBeSaved(ViolationDto violationDto, String vehicleNumber) {

        return ViolationsEntity.builder()
                .type(violationDto.getType())
                .fineAmount(violationDto.getFineAmount())
                .vehicleNumber(vehicleNumber)
                .violationDate(Instant.now())
                .build();
    }

    private String objectToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new VehicleApiException(ApiErrors.INTERNAL_SERVER_ERROR, "Error during object parsing to JSON.");
        }
    }
}
