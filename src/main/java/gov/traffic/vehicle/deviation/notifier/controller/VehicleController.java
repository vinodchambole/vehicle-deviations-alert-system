package gov.traffic.vehicle.deviation.notifier.controller;

import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import gov.traffic.vehicle.deviation.notifier.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Mono<ResponseEntity<VehicleEntity>> addVehicle(@RequestBody VehicleDto vehicle) {
        return vehicleService.addVehicle(vehicle)
                .map(ResponseEntity::ok)
                .doOnError(ex -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @GetMapping("/{vehicleId}")
    public Mono<ResponseEntity<VehicleEntity>> getVehicle(@PathVariable String vehicleId) {
        return vehicleService.getVehicle(vehicleId)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
