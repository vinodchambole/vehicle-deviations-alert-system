package gov.traffic.vehicle.deviation.notifier.repository;

import gov.traffic.vehicle.deviation.notifier.repository.entity.ViolationsEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ViolationRepository extends ReactiveCrudRepository<ViolationsEntity, Long> {
}
