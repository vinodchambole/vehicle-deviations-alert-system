package gov.traffic.vehicle.deviation.notifier.model;

import lombok.Data;

import java.time.Instant;

@Data
public class VehicleDto {
    private String vehicleNumber;
    private String vehicleName;
    private Instant regDate;
    private Instant makeDate;
    private String color;
    private String ownerId;
    private String ownerName;
    private String fasttagId;
    private Fuel fuel;
}
