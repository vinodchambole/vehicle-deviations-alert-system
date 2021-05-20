package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Mono<VehicleEntity> addVehicle(VehicleDto vehicle);

    Mono<VehicleEntity> getVehicle(String vehicleId);
}
