package ru.overcode.booking.validation.reservationdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureOrTodayValidator implements ConstraintValidator<FutureOrToday, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date == null || !date.isBefore(LocalDate.now());
    }
}
