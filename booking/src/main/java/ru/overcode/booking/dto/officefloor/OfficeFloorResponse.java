package ru.overcode.booking.dto.officefloor;

public record OfficeFloorResponse(
        Long officeFloorId,
        String officeName,
        Integer floor
) {

}
