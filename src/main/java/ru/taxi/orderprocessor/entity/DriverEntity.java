package ru.taxi.orderprocessor.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
