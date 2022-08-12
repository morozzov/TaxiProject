package ru.taxi.orderprocessor.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {

    protected UUID id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
