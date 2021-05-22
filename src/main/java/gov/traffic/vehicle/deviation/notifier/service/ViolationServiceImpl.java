package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.exception.ApiErrors;
import gov.traffic.vehicle.deviation.notifier.exception.VehicleApiException;
import gov.traffic.vehicle.deviation.notifier.model.VehicleViolationInfo;
import gov.traffic.vehicle.deviation.notifier.model.ViolationDto;
import gov.traffic.vehicle.deviation.notifier.repository.VehicleRepository;
import gov.traffic.vehicle.deviation.notifier.repository.ViolationRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.ViolationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ViolationRepository violationRepository;


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

    private VehicleViolationInfo getVehicleViolationInfo(gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity vehicleEntity, List<ViolationDto> violationDtos) {
        return new VehicleViolationInfo(vehicleEntity.getVehicleNumber(), vehicleEntity.getVehicleName(), vehicleEntity.getOwnerAdharId(), vehicleEntity.getOwnerName(), TotalFineAmount(violationDtos), violationDtos);
    }

    private Double TotalFineAmount(List<ViolationDto> violationDtos) {
        return violationDtos.stream().map(ViolationDto::getFineAmount).mapToDouble(Double::doubleValue).sum();
    }

    private List<ViolationDto> convertToViolationsDtoList(List<ViolationsEntity> violationsEntityList) {

        return violationsEntityList.stream().map(this::convertToViolationsDto).collect(Collectors.toList());
    }

    private ViolationDto convertToViolationsDto(ViolationsEntity violationsEntity) {
        return new ViolationDto(violationsEntity.getType(), violationsEntity.getFineAmount());
    }

    private ViolationsEntity violationsToBeSaved(ViolationDto violationDto, String vehicleNumber) {

        return ViolationsEntity.builder()
                .type(violationDto.getType())
                .fineAmount(violationDto.getFineAmount())
                .vehicleNumber(vehicleNumber)
                .violationDate(Instant.now())
                .build();
    }
}
