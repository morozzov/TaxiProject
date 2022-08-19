package ru.taxi.orderprocessor.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.taxi.orderprocessor.dto.CarCreateUpdateOperationDto;
import ru.taxi.orderprocessor.enums.PriorityClass;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class CarOperationsProcessor {

    @Value("${internal.cars.max-year}")
    private Integer maxYear;
    @Value("${internal.cars.offset}")
    private Integer offset;

    public PriorityClass doCalculateClass(CarCreateUpdateOperationDto dto) {
        var between = Period.between(LocalDate.now(), dto.getIssuedAt());
        var carAge =  between.getYears();
        if (carAge > maxYear) {
            throw new IllegalStateException("Exceed allowed time period for car exception!");
        }

        PriorityClass rawPriorityClass = PriorityClass.convert(dto.getCarClass());
        Integer downgradesToApply = doCalculateDowngrades(carAge);

        rawPriorityClass = downgradesToApply > rawPriorityClass.ordinal() ?
                PriorityClass.values()[0] :
                PriorityClass.values()[rawPriorityClass.ordinal() - downgradesToApply];

        return rawPriorityClass;
    }

    public Integer doCalculateDowngrades(Integer carAge) {
        int downgrades = carAge / offset;
        int tail = carAge % offset;
        if (tail == 0) {
            downgrades -= 1;
        }
        return downgrades;
    }
}
