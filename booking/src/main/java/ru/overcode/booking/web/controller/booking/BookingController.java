package ru.overcode.booking.web.controller.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.overcode.booking.dto.booking.BookSeatRequest;
import ru.overcode.booking.service.booking.BookingService;
import ru.overcode.booking.web.ApiV1;
import ru.overcode.booking.web.response.Response;

@ApiV1
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public Response<?> bookSeat(@Valid @RequestBody BookSeatRequest request) {
        bookingService.bookSeat(request.date(), request.seatId());
        return Response.success();
    }
}
