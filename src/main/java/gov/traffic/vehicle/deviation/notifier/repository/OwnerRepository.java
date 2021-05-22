package gov.traffic.vehicle.deviation.notifier.repository;

import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleOwnerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OwnerRepository extends ReactiveCrudRepository<VehicleOwnerEntity, String> {
}
