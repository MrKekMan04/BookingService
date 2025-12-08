package ru.overcode.booking.dto.seat;

import java.math.BigDecimal;

public record SeatOnOfficeFloorResponse(
        Long seatId,
        BookedClientDto bookedBy,
        BigDecimal xPosition,
        BigDecimal yPosition,
        Integer rotation
) {
}
