package ru.taxi.orderprocessor.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.taxi.orderprocessor.dao.CarOperationsRepository;
import ru.taxi.orderprocessor.dao.custom.CarOperationsRepositoryCustom;
import ru.taxi.orderprocessor.dto.CarCreateUpdateOperationDto;
import ru.taxi.orderprocessor.dto.CarDto;
import ru.taxi.orderprocessor.dto.FindCarsCriteria;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.enums.PriorityClass;
import ru.taxi.orderprocessor.mapper.CarMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarOperationsService {

    private final CarOperationsRepository repository;
    private final CarOperationsRepositoryCustom repositoryCustom;
    private final CarOperationsProcessor processor;
    private final CarMapper mapper;

    public CarDto create(CarCreateUpdateOperationDto dto) {
        log.debug("create.in - dto: {}", dto);
        var carEntity = mapper.dtoToEntity(dto);
        PriorityClass priorityClass = processor.doCalculateClass(dto);
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
        var carEntity = findByNumberInternal(stateNumber);
        log.info("update.in - found car record with number: {}", stateNumber);
        var entityUpdated = mapper.updateFromDto(dto, carEntity);

        PriorityClass priorityClass = processor.doCalculateClass(dto);
        entityUpdated.setPriorityClass(priorityClass);

        var updatedPersisted = repository.save(entityUpdated);
        log.info("update.in - car record with number {} updated", stateNumber);
        var updatedPersistedDto = mapper.entityToDto(updatedPersisted);
        log.debug("update.out - response: {}", updatedPersistedDto);
        return updatedPersistedDto;
    }

    public List<CarDto> find(FindCarsCriteria criteria) {
        log.info("find.in - searching cars by criteria: {}", criteria);
        List<CarEntity> carEntities = repositoryCustom.find(criteria.getCriteria(), criteria.getSort());
        var carDtos = mapper.entityToDto(carEntities);
        log.info("find.o - result: {}", carDtos);
        return carDtos;
    }

    public CarDto findByNumber(String stateNumber) {
        return mapper.entityToDto(findByNumberInternal(stateNumber));
    }

    public CarEntity findByNumberInternal(String stateNumber) {
        return repository.findByNumber(stateNumber).orElseThrow(() ->{
            throw new EntityNotFoundException(String.format("Car with number %s not found in registry.", stateNumber));
        });
    }
}
