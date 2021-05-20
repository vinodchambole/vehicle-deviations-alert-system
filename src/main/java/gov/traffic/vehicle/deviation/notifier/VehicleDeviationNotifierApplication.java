package gov.traffic.vehicle.deviation.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class VehicleDeviationNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleDeviationNotifierApplication.class, args);
    }

}
