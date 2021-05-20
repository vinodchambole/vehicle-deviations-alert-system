package gov.traffic.vehicle.deviation.notifier.repository;

import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VehicleRepository extends ReactiveCrudRepository<VehicleEntity, String> {
}
