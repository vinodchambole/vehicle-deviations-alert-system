package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.exception.ApiErrors;
import gov.traffic.vehicle.deviation.notifier.exception.VehicleApiException;
import gov.traffic.vehicle.deviation.notifier.model.ViolationDto;
import gov.traffic.vehicle.deviation.notifier.repository.VehicleRepository;
import gov.traffic.vehicle.deviation.notifier.repository.ViolationRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.ViolationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

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

    private ViolationsEntity violationsToBeSaved(ViolationDto violationDto, String vehicleNumber) {

        return ViolationsEntity.builder()
                .type(violationDto.getType())
                .fineAmount(violationDto.getFineAmount())
                .vehicleNumber(vehicleNumber)
                .violationDate(Instant.now())
                .build();
    }
}
