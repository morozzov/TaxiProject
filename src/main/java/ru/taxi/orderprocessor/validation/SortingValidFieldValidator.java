package ru.taxi.orderprocessor.validation;

import lombok.extern.slf4j.Slf4j;
import ru.taxi.orderprocessor.dto.Sort;
import ru.taxi.orderprocessor.entity.CarEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import static java.lang.String.format;

@Slf4j
public class SortingValidFieldValidator implements ConstraintValidator<ValidSortingField, Object> {

    @Override
    public void initialize(ValidSortingField constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        log.info("SortingValidFieldValidator - initialized");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(CarEntity.class.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(String.valueOf(value)));
    }
}
