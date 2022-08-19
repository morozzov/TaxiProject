package ru.taxi.orderprocessor.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.taxi.orderprocessor.dto.CarCreateUpdateOperationDto;
import ru.taxi.orderprocessor.dto.CarDto;
import ru.taxi.orderprocessor.dto.FindCarsCriteria;
import ru.taxi.orderprocessor.logic.CarOperationsService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarOperationsController {

    private final CarOperationsService carOperationsService;

    @PostMapping
    public CarDto createCar(@Valid @RequestBody CarCreateUpdateOperationDto createCarDto) {
        log.debug("createCar.in - dto: {}", createCarDto);
        var carEntity = carOperationsService.create(createCarDto);
        log.debug("createCar.out - response: {}", carEntity);
        return carEntity;
    }

    @PutMapping
    public CarDto updateCar(@Valid @RequestBody CarCreateUpdateOperationDto updateCarDto) {
        log.debug("updateCar.in - dto: {}", updateCarDto);
        var carEntity = carOperationsService.update(updateCarDto);
        log.debug("updateCar.out - response: {}", carEntity);
        return carEntity;
    }

    @GetMapping("/{number}")
    public CarDto findCarByNumber(@PathVariable String number) {
        return carOperationsService.findByNumber(number);
    }

    @PostMapping("/find")
    public List<CarDto> findCars (@Valid @RequestBody FindCarsCriteria findCarsCriteria) {
        return carOperationsService.find(findCarsCriteria);
    }
}
