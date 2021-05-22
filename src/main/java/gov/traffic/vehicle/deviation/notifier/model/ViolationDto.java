package gov.traffic.vehicle.deviation.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ViolationDto {

    private String type;

    private Double fineAmount;

    private Instant violationDate;
}
