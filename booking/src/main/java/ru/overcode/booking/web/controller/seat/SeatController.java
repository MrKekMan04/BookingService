package ru.overcode.booking.web.controller.seat;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.overcode.booking.dto.seat.SeatOnOfficeFloorRequest;
import ru.overcode.booking.dto.seat.SeatOnOfficeFloorResponse;
import ru.overcode.booking.service.seat.SeatService;
import ru.overcode.booking.web.ApiV1;
import ru.overcode.booking.web.response.ListResponse;

@ApiV1
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/seats")
    public ListResponse<SeatOnOfficeFloorResponse> getAllSeatsOnOfficeFloor(
            @RequestBody @Valid SeatOnOfficeFloorRequest seatOnOfficeFloorRequest
    ) {
        return ListResponse.success(seatService.getAllSeatsOnOfficeFloor(seatOnOfficeFloorRequest));
    }
}
