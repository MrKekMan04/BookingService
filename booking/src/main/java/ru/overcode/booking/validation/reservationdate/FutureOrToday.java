package ru.overcode.booking.validation.reservationdate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureOrTodayValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureOrToday {
    String message() default "Дата не может быть раньше текущей";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
