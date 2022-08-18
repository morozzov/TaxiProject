package ru.taxi.orderprocessor.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.taxi.orderprocessor.dao.CarOperationsRepository;
import ru.taxi.orderprocessor.dto.CarCreateUpdateOperationDto;
import ru.taxi.orderprocessor.dto.CarDto;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.enums.PriorityClass;
import ru.taxi.orderprocessor.mapper.CarMapper;

import javax.persistence.EntityNotFoundException;
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

    public CarDto create(CarCreateUpdateOperationDto dto) {
        log.debug("create.in - dto: {}", dto);
        var carEntity = mapper.dtoToEntity(dto);
        PriorityClass priorityClass = doCalculateClass(dto);
        carEntity.setPriorityClass(priorityClass);
        log.info("create.in - calculated priority class: {}", priorityClass);
        var createdCar = repository.save(carEntity);
        log.info("create.out - created car_entity with id: {}", createdCar.getId());
        var carDto = mapper.entityToDto(createdCar);
        log.debug("create.out - response: {}", carDto);
        return carDto;
    }

    public CarDto update(CarCreateUpdateOperationDto dto) {
        log.debug("update.in - dto: {}", dto);
        String stateNumber = dto.getStateNumber();
        var carEntity = repository.findByNumber(stateNumber).orElseThrow(() ->{
            throw new EntityNotFoundException(String.format("Car with number %s not found in repository", stateNumber));
        });
        log.info("update.in - found car record with number: {}", stateNumber);
        var entityUpdated = mapper.updateFromDto(dto, carEntity);

        PriorityClass priorityClass = doCalculateClass(dto);
        entityUpdated.setPriorityClass(priorityClass);

        var updatedPersisted = repository.save(entityUpdated);
        log.info("update.in - car record with number {} updated", stateNumber);
        var updatedPersistedDto = mapper.entityToDto(updatedPersisted);
        log.debug("update.out - response: {}", updatedPersistedDto);
        return updatedPersistedDto;
    }

    private PriorityClass doCalculateClass(CarCreateUpdateOperationDto dto) {
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

    private Integer doCalculateDowngrades(Integer carAge) {
        int downgrades = carAge / offset;
        int tail = carAge % offset;
        if (tail == 0) {
            downgrades -= 1;
        }
        return downgrades;
    }
}
