package gov.traffic.vehicle.deviation.notifier.service;

import gov.traffic.vehicle.deviation.notifier.exception.ApiErrors;
import gov.traffic.vehicle.deviation.notifier.exception.VehicleApiException;
import gov.traffic.vehicle.deviation.notifier.model.VehicleOwnerDto;
import gov.traffic.vehicle.deviation.notifier.repository.OwnerRepository;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleOwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Mono<VehicleOwnerDto> getOwner(String adharNumber) {
        return ownerRepository.findById(adharNumber)
                .map(this::onvertToDto)
                .switchIfEmpty(Mono.error(() -> new VehicleApiException(ApiErrors.NOT_FOUND, "Owner data not found with given adhar id.")));
    }

    @Override
    public Mono<String> deductFasttagBalance(String adharNumber, Double fineAmount) {
        return ownerRepository.findById(adharNumber)
                .map(vehicleOwnerEntity -> {
                    Double fasttagBalance = vehicleOwnerEntity.getFasttagBalance();
                    double result = fasttagBalance - fineAmount;
                    vehicleOwnerEntity.setFasttagBalance(result);
                    return vehicleOwnerEntity;
                })
                .flatMap(vehicleOwnerEntity -> ownerRepository.save(vehicleOwnerEntity))
                .map(VehicleOwnerEntity::getOwnerAdharId);
    }

    private VehicleOwnerDto onvertToDto(VehicleOwnerEntity vehicleOwnerEntity) {
        return VehicleOwnerDto.builder()
                .adharNumber(vehicleOwnerEntity.getOwnerAdharId())
                .name(vehicleOwnerEntity.getOwnerName())
                .phNumber(vehicleOwnerEntity.getPhoneNumber())
                .address(vehicleOwnerEntity.getAddress())
                .fastTagId(vehicleOwnerEntity.getFasttagId())
                .fastTagBalance(vehicleOwnerEntity.getFasttagBalance())
                .build();
    }
}
