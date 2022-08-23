package ru.taxi.orderprocessor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.taxi.orderprocessor.dto.DriverDto;
import ru.taxi.orderprocessor.entity.DriverEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DriverMapper {

    DriverEntity map(DriverDto dto);
}
