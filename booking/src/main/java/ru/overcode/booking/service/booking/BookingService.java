package ru.overcode.booking.service.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.officefloor.OfficeFloor;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.entity.reservation.ReservationId;
import ru.overcode.booking.entity.seat.Seat;
import ru.overcode.booking.exception.ExceptionMessage;
import ru.overcode.booking.exception.error.UnprocessableEntityException;
import ru.overcode.booking.service.officefloor.OfficeFloorDbService;
import ru.overcode.booking.service.reservation.FreeFixedReservationDbService;
import ru.overcode.booking.service.reservation.ReservationDbService;
import ru.overcode.booking.service.seat.SeatDbService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {

    private static final Long DUMMY_CLIENT_ID = -1L;

    private final SeatDbService seatDbService;
    private final OfficeFloorDbService officeFloorDbService;
    private final ReservationDbService reservationDbService;
    private final FreeFixedReservationDbService freeFixedReservationDbService;

    @Transactional
    public void bookSeat(LocalDate date, Long seatId) {
        Seat seat = seatDbService.findById(seatId)
                .orElseThrow(() -> new UnprocessableEntityException(ExceptionMessage.SEAT_NOT_FOUND));
        List<Long> officeFloorIds = findAllOfficeFloorIds(seat.getOfficeFloorId());

        validateClientHasActiveBooking(DUMMY_CLIENT_ID, officeFloorIds, date);
        validateSeatAlreadyBooked(seat, date);

        reservationDbService.save(
                new Reservation()
                        .setId(
                                new ReservationId()
                                        .setReservationDate(date)
                                        .setTableId(seatId)
                        )
                        .setClientId(DUMMY_CLIENT_ID)
        );
    }

    private void validateClientHasActiveBooking(
            Long clientId,
            List<Long> officeFloorIds,
            LocalDate date
    ) {
        seatDbService.findClientFixedOfficeSeat(clientId, officeFloorIds)
                .ifPresent(fixedSeat -> {
                    throw new UnprocessableEntityException(ExceptionMessage.CLIENT_HAS_FIXED_SEAT);
                });

        reservationDbService.findByClientIdOnDate(clientId, date)
                .ifPresent(activeReservation -> {
                    throw new UnprocessableEntityException(ExceptionMessage.CLIENT_HAS_ACTIVE_BOOKING);
                });
    }

    private void validateSeatAlreadyBooked(Seat seat, LocalDate date) {
        if (seat.getFixedClientId() != null) {
            Set<Long> tableFreeReservations =
                    freeFixedReservationDbService.findAllByTableIdsAndReservationDate(Set.of(seat.getId()), date);
            if (tableFreeReservations.isEmpty()) {
                throw new UnprocessableEntityException(ExceptionMessage.SEAT_ALREADY_BOOKED);
            }
        }

        List<Reservation> tableReservations =
                reservationDbService.findAllByTableIdsAndReservationDate(Set.of(seat.getId()), date);
        if (!tableReservations.isEmpty()) {
            throw new UnprocessableEntityException(ExceptionMessage.SEAT_ALREADY_BOOKED);
        }
    }

    private List<Long> findAllOfficeFloorIds(Long officeFloorId) {
        return officeFloorDbService.findById(officeFloorId)
                .map(OfficeFloor::getOfficeName)
                .map(officeFloorDbService::findAllOfficeFloorIdsByOfficeName)
                .orElseThrow(() -> new UnprocessableEntityException(ExceptionMessage.OFFICE_FLOOR_NOT_FOUND));
    }
}
