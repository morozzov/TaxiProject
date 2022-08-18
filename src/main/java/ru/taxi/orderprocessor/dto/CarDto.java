package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.enums.PriorityClass;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private String model;
    private String stateNumber;
    private String color;
    private LocalDate issuedAt;
    private CarEntity.CarClass carClass;
    private PriorityClass priorityClass;
}
