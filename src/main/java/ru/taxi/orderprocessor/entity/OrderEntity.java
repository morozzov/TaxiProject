package ru.taxi.orderprocessor.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderEntity extends BaseEntity  {

    private DriverEntity driver;
    private ClientEntity client;

    private PriceStrategyEntity priceStrategy;
    private RouteEntity route;
    private RateEntity rate;

    private OrderStatus status;


    public enum OrderStatus {
        CREATED,
        DRIVER_WAITING,
        CLIENT_WAITING,
        IN_PROGRESS,
        FINISHED
    }
}
