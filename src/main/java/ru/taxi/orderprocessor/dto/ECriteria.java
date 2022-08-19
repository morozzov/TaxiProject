package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.enums.PriorityClass;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECriteria {

    private CarEntity.CarClass carClass;
    private String color;
    private PriorityClass priorityClass;
    private LocalDate issuedAtGreater;
    private LocalDate issuedAtLower;
    private String model;
}
