package gov.traffic.vehicle.deviation.notifier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Controller {

    @GetMapping
    public String hello() {
        return "This application notify traffic police with the vehicle info which has old pending fines.";
    }
}
