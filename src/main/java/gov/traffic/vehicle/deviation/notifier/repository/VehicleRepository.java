package gov.traffic.vehicle.deviation.notifier.repository;

import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface VehicleRepository extends ReactiveCrudRepository<VehicleEntity, String> {

    Mono<VehicleEntity> findByVehicleNumber(String vehicleNumber);
}
