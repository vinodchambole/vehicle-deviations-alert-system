package gov.traffic.vehicle.deviation.notifier.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("vehicle_owner")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleOwnerEntity {
    @Id
    private String ownerAdharId;
    @Column
    private String ownerName;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String fasttagId;
    @Column
    private Double fasttagBalance;
}
