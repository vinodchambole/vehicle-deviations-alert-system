package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.model.VehicleOwnerDto;
import reactor.core.publisher.Mono;

public interface OwnerService {
    Mono<VehicleOwnerDto> getOwner(String adharNumber);

    Mono<String> deductFasttagBalance(String adharNumber, Double fineAmount);
}
