package ru.taxi.orderprocessor.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taxi.orderprocessor.dto.DriverDto;
import ru.taxi.orderprocessor.entity.DriverEntity;
import ru.taxi.orderprocessor.logic.DriverOperationsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverOperationsController  {

    private final DriverOperationsService driverOperationsService;

    @PostMapping
    public DriverEntity create (@RequestBody DriverDto dto) {
        return driverOperationsService.create(dto);
    }
}
