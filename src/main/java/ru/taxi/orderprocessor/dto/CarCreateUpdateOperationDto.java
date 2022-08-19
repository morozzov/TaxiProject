package ru.taxi.orderprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.taxi.orderprocessor.entity.CarEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarCreateUpdateOperationDto {

    @NotBlank
    private String model;
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "" +
            "Must comply with the format of the state mark of the Russian Federation(А123ВС45).")
    @NotBlank
    private String stateNumber;
    @NotBlank
    private String color;
    @NotNull
    private LocalDate issuedAt;
    @NotNull
    private CarEntity.CarClass carClass;
}
