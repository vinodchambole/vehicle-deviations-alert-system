package gov.traffic.vehicle.deviation.notifier.repository.entity;

import gov.traffic.vehicle.deviation.notifier.model.Fuel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleEntity {

    @Id
    private String vehicleNumber;
    @Column
    private String vehicleName;
    @Column
    private Instant regDate;
    @Column
    private Instant makeDate;
    @Column
    private String color;
    @Column
    private String ownerId;
    @Column
    private String ownerName;
    @Column
    private String fasttagId;
    @Column
    private Fuel fuel;
}
