package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.model.VehicleResource;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import reactor.core.publisher.Mono;

public interface VehicleService {

    Mono<VehicleResource> addVehicle(VehicleDto vehicle);

    Mono<VehicleEntity> getVehicle(String vehicleId);
}
