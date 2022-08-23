package ru.taxi.orderprocessor.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "drivers")
public class DriverEntity extends BaseEntity {

    private String firstName;
    private String secondName;

    private String licenseNumber;
    private String drivingExp;

    private DriverStatus status;

    @OneToOne
    private CarEntity car;

    private boolean terminated;

    public enum DriverStatus {
        FREE,
        ORDER_IN_PROGRESS,
        OFFLINE
    }
}
