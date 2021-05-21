package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.exception.ApiErrors;
import gov.traffic.vehicle.deviation.notifier.exception.VehicleApiException;
import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.model.VehicleResource;
import gov.traffic.vehicle.deviation.notifier.repository.VehicleRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Mono<VehicleResource> addVehicle(VehicleDto vehicle) {
        validateRequest(vehicle);

        return vehicleRepository.findByVehicleNumber(vehicle.getVehicleNumber())
                .switchIfEmpty(Mono.defer(() -> vehicleRepository.save(getVehicleEntity(vehicle))))
                .map(VehicleEntity::getVehicleNumber)
                .map(VehicleResource::new);
    }

    private VehicleEntity getVehicleEntity(VehicleDto vehicle) {
        return VehicleEntity.builder()
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleName(vehicle.getVehicleName())
                .makeDate(vehicle.getMakeDate())
                .regDate(vehicle.getRegDate())
                .ownerName(vehicle.getOwnerName())
                .manufacturer(vehicle.getManufacturer())
                .ownerAdharId(vehicle.getOwnerAdharId())
                .color(vehicle.getColor())
                .fuel(vehicle.getFuel())
                .build();
    }

    private void validateRequest(VehicleDto vehicle) {
        if (null == vehicle.getVehicleNumber() || vehicle.getVehicleNumber().isEmpty()) {
            throw new VehicleApiException(ApiErrors.BAD_REQUEST, "vehicleNumber is required.");
        }
    }

    @Override
    public Mono<VehicleEntity> getVehicle(String vehicleId) {
        return vehicleRepository.findByVehicleNumber(vehicleId)
                .switchIfEmpty(Mono.error(() -> new VehicleApiException(ApiErrors.NOT_FOUND, "vehicle data with does not exist.")));
    }
}
