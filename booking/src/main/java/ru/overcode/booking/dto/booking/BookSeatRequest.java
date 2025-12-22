package ru.overcode.booking.dto.booking;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookSeatRequest(
        @NotNull(message = "`date` не может быть пустым")
        @FutureOrPresent(message = "`date` не может быть раньше сегодняшней")
        LocalDate date,
        @NotNull(message = "`seatId` не может быть пустым")
        Long seatId
) {
}
