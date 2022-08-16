package ru.taxi.orderprocessor.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taxi.orderprocessor.dto.CreateCarDto;
import ru.taxi.orderprocessor.entity.CarEntity;
import ru.taxi.orderprocessor.logic.CarOperationsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarOperationsController {

    private final CarOperationsService carOperationsService;

    @PostMapping
    public CarEntity createCar(@RequestBody CreateCarDto createCarDto) {
        log.debug("createCar.in - dto: {}", createCarDto);
        CarEntity carEntity = carOperationsService.create(createCarDto);
        log.debug("createCar.out - response: {}", carEntity);
        return carEntity;
    }
}
