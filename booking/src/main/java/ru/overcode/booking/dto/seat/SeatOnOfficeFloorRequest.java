package ru.overcode.booking.dto.seat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import ru.overcode.booking.validation.reservationdate.FutureOrToday;

import java.time.LocalDate;

public record SeatOnOfficeFloorRequest(
        @NotNull(message = "`date` не может быть пустым")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "`date` должна быть в формате yyyy-MM-dd")
        @FutureOrToday(message = "`date` не может быть раньше сегодняшней")
        LocalDate date,
        @NotNull(message = "`officeFloorId` не может быть пустым")
        @Positive(message = "``officeFloorId` не может быть отрицательным`")
        Long officeFloorId
) {
}
