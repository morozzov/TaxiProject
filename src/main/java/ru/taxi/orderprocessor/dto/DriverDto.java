package ru.taxi.orderprocessor.dto;

import lombok.*;
import ru.taxi.orderprocessor.entity.DriverEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {

    private String firstName;
    private String secondName;
    private String licenseNumber;
    private String drivingExp;
    private DriverEntity.DriverStatus status;
    private boolean terminated;
    private String carNumber;
}
