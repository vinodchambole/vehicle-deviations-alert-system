package gov.traffic.vehicle.deviation.notifier.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("violations")
@Builder
public class ViolationsEntity {

    @Id
    private long id;

    @Column
    private String type;

    @Column
    private Double fineAmount;

    @Column
    private Instant violationDate;

    @Column
    private String vehicleNumber;
}
