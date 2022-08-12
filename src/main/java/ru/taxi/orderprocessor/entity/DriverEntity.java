package ru.taxi.orderprocessor.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class DriverEntity extends BaseEntity {

    private String firstName;
    private String secondName;

    private String licenseNumber;
    private String drivingExp;

    private DriverStatus status;
    private CarEntity car;

    private boolean terminated;


    public enum DriverStatus {
        FREE,
        ORDER_IN_PROGRESS,
        OFFLINE
    }
}
