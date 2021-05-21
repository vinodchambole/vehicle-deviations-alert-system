package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.repository.VehicleRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Mono<VehicleEntity> addVehicle(VehicleDto vehicle) {

        validateRequest(vehicle);
        VehicleEntity vehicleEntity = VehicleEntity.builder()
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleName(vehicle.getVehicleName())
                .makeDate(vehicle.getMakeDate())
                .regDate(vehicle.getRegDate())
                .ownerName(vehicle.getOwnerName())
                .ownerAdharId(vehicle.getOwnerAdharId())
                .color(vehicle.getColor())
                .fuel(vehicle.getFuel())
                .build();

        return vehicleRepository.save(vehicleEntity);
    }

    private void validateRequest(VehicleDto vehicle) {
        if (vehicle.getVehicleNumber().isEmpty()) {
            throw new RuntimeException("vehicle number is required");
        }
        String vehicleNumber = vehicle.getVehicleNumber();
        vehicleRepository.findByVehicleNumber(vehicleNumber);
    }

    @Override
    public Mono<VehicleEntity> getVehicle(String vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }
}
