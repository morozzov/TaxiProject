package ru.taxi.orderprocessor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.taxi.orderprocessor.dto.CarDto;
import ru.taxi.orderprocessor.dto.CarCreateUpdateOperationDto;
import ru.taxi.orderprocessor.entity.CarEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    @Mapping(source = "stateNumber", target = "number")
    @Mapping(target = "priorityClass", ignore = true)
    CarEntity dtoToEntity(CarCreateUpdateOperationDto createCarDto);

    @Mapping(source = "number", target = "stateNumber")
    CarDto entityToDto(CarEntity carEntity);

    CarEntity updateFromDto(CarCreateUpdateOperationDto source, @MappingTarget CarEntity target);

    List<CarDto> entityToDto(Collection<CarEntity> target);
}
