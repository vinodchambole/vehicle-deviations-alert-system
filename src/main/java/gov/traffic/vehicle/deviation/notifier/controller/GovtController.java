package gov.traffic.vehicle.deviation.notifier.controller;

import gov.traffic.vehicle.deviation.notifier.model.VehicleOwnerDto;
import gov.traffic.vehicle.deviation.notifier.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gov")
public class GovtController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/vehicle-owner/{aadharNumber}")
    public Mono<VehicleOwnerDto> getViolations(@PathVariable String aaadharNumber) {
        return ownerService.getOwner(aaadharNumber);
    }

    @GetMapping("/vehicle/fasttag-deduct/{aadharNumber}")
    public Mono<String> deductFastTagBalance(@PathVariable String aadharNumber, @RequestParam Double fineAmount) {
        return ownerService.deductFasttagBalance(aadharNumber, fineAmount);
    }
}
