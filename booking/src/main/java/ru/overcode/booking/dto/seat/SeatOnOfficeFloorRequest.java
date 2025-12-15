package ru.overcode.booking.dto.seat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record SeatOnOfficeFloorRequest(
        @NotNull(message = "`date` не может быть пустым")
        @FutureOrPresent(message = "`date` не может быть раньше сегодняшней")
        LocalDate date,
        @NotNull(message = "`officeFloorId` не может быть пустым")
        @Positive(message = "`officeFloorId` не может быть отрицательным`")
        Long officeFloorId
) {
}
