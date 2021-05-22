package gov.traffic.vehicle.deviation.notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDeviationNotificationMessage {

    private String vehicleNumber;

    private String locationCoordinates;
}
