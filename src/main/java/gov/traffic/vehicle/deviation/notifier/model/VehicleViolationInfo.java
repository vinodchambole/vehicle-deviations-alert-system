package gov.traffic.vehicle.deviation.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleViolationInfo {

    private String vehicleNumber;

    private String vehicleName;

    private String ownerAdharId;

    private String ownerName;

    private Double totalFine;

    private List<ViolationDto> violations;

}
