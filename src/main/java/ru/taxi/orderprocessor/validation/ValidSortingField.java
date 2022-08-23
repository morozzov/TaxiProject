package ru.taxi.orderprocessor.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SortingValidFieldValidator.class)
public @interface ValidSortingField {

    String message() default  "Invalid sorting field name passed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
