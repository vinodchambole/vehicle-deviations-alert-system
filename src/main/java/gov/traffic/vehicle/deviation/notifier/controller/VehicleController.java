package gov.traffic.vehicle.deviation.notifier.controller;

import gov.traffic.vehicle.deviation.notifier.model.VehicleDto;
import gov.traffic.vehicle.deviation.notifier.model.VehicleResource;
import gov.traffic.vehicle.deviation.notifier.repository.entity.VehicleEntity;
import gov.traffic.vehicle.deviation.notifier.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Mono<ResponseEntity<VehicleResource>> addVehicle(@RequestBody Mono<VehicleDto> vehicle) {
        return vehicle.flatMap(vehicleDto -> vehicleService.addVehicle(vehicleDto))
                .map(ResponseEntity::ok)
                .log();
    }

    @GetMapping("/{vehicleNumber}")
    public Mono<ResponseEntity<VehicleEntity>> getVehicle(@PathVariable String vehicleNumber) {
        return vehicleService.getVehicle(vehicleNumber)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
