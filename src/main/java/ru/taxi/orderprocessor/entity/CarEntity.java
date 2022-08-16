package ru.taxi.orderprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.taxi.orderprocessor.enums.PriorityClass;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cars")
public class CarEntity extends BaseEntity {

    private String model;

    private String number;

    private String color;

    private LocalDate issuedAt;

    @Enumerated(EnumType.STRING)
    private CarClass carClass;

    @Enumerated(EnumType.STRING)
    private PriorityClass priorityClass;

    public enum CarClass {
        A, B, C, E, D, F
    }
}
