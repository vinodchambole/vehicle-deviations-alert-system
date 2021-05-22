package gov.traffic.vehicle.deviation.notifier.controller;

import gov.traffic.vehicle.deviation.notifier.model.VehicleViolationInfo;
import gov.traffic.vehicle.deviation.notifier.model.ViolationDto;
import gov.traffic.vehicle.deviation.notifier.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    @PostMapping("/{vehicleNumber}")
    public Mono<ResponseEntity<String>> addViolations(@RequestBody Mono<ViolationDto> violations, @PathVariable String vehicleNumber) {
        return violations.
                flatMap(violationsList -> violationService.addVoilations(violationsList, vehicleNumber)
                        .map(ResponseEntity::ok));
    }

    @GetMapping("/{vehicleNumber}")
    public Mono<VehicleViolationInfo> getViolations(@PathVariable String vehicleNumber) {
        return violationService.getViolations(vehicleNumber);
    }


}
