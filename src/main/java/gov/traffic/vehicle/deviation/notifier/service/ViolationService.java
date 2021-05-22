package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.model.ViolationDto;
import reactor.core.publisher.Mono;

public interface ViolationService {
    Mono<String> addVoilations(ViolationDto violationsFlux, String vehicleNumber);
}
