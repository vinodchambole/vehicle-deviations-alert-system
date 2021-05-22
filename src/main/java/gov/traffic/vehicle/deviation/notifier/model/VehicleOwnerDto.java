package gov.traffic.vehicle.deviation.notifier.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleOwnerDto {
    private String adharNumber;
    private String name;
    private String phNumber;
    private String address;
    private String fastTagId;
    private Double fastTagBalance;
}
