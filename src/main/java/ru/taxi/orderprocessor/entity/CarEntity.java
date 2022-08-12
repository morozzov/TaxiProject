package ru.taxi.orderprocessor.entity;

import ru.taxi.orderprocessor.enums.PriorityClass;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarEntity extends BaseEntity {

    private String model;

    private String number;

    private String color;

    private String servingClass;

    private PriorityClass carClass;
}
