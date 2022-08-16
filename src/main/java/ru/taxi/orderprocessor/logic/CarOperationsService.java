package ru.taxi.orderprocessor.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.taxi.orderprocessor.dao.CarOperationsRepository;
import ru.taxi.orderprocessor.dto.CreateCarDto;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.enums.PriorityClass;
import ru.taxi.orderprocessor.mapper.CarMapper;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarOperationsService {

    private final CarOperationsRepository repository;
    private final CarMapper mapper;

    @Value("${internal.cars.max-year}")
    private Integer maxYear;
    @Value("${internal.cars.offset}")
    private Integer offset;

    public CarEntity create(CreateCarDto dto) {
        log.debug("create.in - dto: {}", dto);
        var carEntity = mapper.dtoToEntity(dto);

        PriorityClass priorityClass = doCalculateClass(dto);
        carEntity.setPriorityClass(priorityClass);
        log.info("create.in - calculated priority class: {}", priorityClass);
        var createdCar = repository.save(carEntity);
        log.info("create.out - created car_entity with id: {}", createdCar.getId());
        log.debug("create.out - response: {}", createdCar);
        return null;
    }

    private PriorityClass doCalculateClass(CreateCarDto dto) {
        var between = Period.between(LocalDate.now(), dto.getIssuedAt());
        var carAge =  between.getYears();
        if (carAge > maxYear) {
            throw new IllegalStateException("Exceed allowed time period for car exception!");
        }

        PriorityClass rawPriorityClass = PriorityClass.convert(dto.getCarClass());
        Integer downgradesToApply = doCalculateDowngrades(carAge);

        switch (rawPriorityClass) {
            //todo normal calculate
        }
        return rawPriorityClass;
    }

    private Integer doCalculateDowngrades(Integer carAge) {
        int downgrades = carAge / offset;
        int tail = carAge % offset;
        if (tail == 0) {
            downgrades -= 1;
        }
        return downgrades;
    }
}
