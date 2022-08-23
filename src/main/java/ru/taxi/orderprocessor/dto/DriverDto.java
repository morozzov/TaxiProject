package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
