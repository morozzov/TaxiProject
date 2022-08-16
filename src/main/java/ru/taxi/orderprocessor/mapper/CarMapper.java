package ru.taxi.orderprocessor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.taxi.orderprocessor.dto.CreateCarDto;
import ru.taxi.orderprocessor.entity.CarEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    @Mapping(source = "stateNumber", target = "number")
    @Mapping(target = "priorityClass", ignore = true)
    CarEntity dtoToEntity(CreateCarDto createCarDto);
}
