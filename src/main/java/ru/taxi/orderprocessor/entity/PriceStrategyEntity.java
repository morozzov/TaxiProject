package ru.taxi.orderprocessor.entity;

import ru.taxi.orderprocessor.enums.PriorityClass;

public class PriceStrategyEntity extends BaseEntity {

    private PriorityClass priorityClass;
    private TariffEntity tariff;
    private Double waitTimePrice;

    private Double totalPrice; //calculated
}
