package ru.overcode.booking.service.seat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.dto.seat.SeatOnOfficeFloorRequest;
import ru.overcode.booking.dto.seat.SeatOnOfficeFloorResponse;
import ru.overcode.booking.entity.client.Client;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.entity.seat.Seat;
import ru.overcode.booking.mapper.seat.SeatMapper;
import ru.overcode.booking.service.client.ClientDbService;
import ru.overcode.booking.service.reservation.FreeFixedReservationDbService;
import ru.overcode.booking.service.reservation.ReservationDbService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatDbService seatDbService;
    private final FreeFixedReservationDbService freeFixedReservationDbService;
    private final ReservationDbService reservationDbService;
    private final ClientDbService clientDbService;
    private final SeatMapper seatMapper;

    @Transactional(readOnly = true)
    public List<SeatOnOfficeFloorResponse> getAllSeatsOnOfficeFloor(SeatOnOfficeFloorRequest seatOnOfficeFloorRequest) {
        Map<Boolean, Map<Long, Seat>> seats = seatDbService.getSeatsForOfficeFloor(seatOnOfficeFloorRequest.officeFloorId());

        Map<Long, Seat> fixedSeats = seats.getOrDefault(true, Map.of());
        Map<Long, Seat> regularSeats = seats.getOrDefault(false, Map.of());

        Map<Long, Long> clientIdsByTableIds = Stream.concat(
                        getClientsIdsForFixedSeats(fixedSeats, seatOnOfficeFloorRequest.date()).entrySet().stream(),
                        getClientIdsForRegularSeats(regularSeats, seatOnOfficeFloorRequest.date()).entrySet().stream()
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue)
                );

        Map<Long, Client> clientsById = clientDbService.findAllClientsByIds(clientIdsByTableIds.keySet());

        return seatMapper.toSeatOnOfficeFloorResponse(seats, clientIdsByTableIds, clientsById);
    }

    private Map<Long, Long> getClientsIdsForFixedSeats(Map<Long, Seat> fixedSeats, LocalDate date) {
        Set<Long> freeFixedReservationTableIds = freeFixedReservationDbService.findAllByTableIdsAndReservationDate(fixedSeats.keySet(), date);

        return fixedSeats.values().stream()
                .filter(seat -> !freeFixedReservationTableIds.contains(seat.getId()))
                .collect(Collectors.toMap(
                        Seat::getId,
                        Seat::getFixedClientId
                ));
    }

    private Map<Long, Long> getClientIdsForRegularSeats(Map<Long, Seat> regularSeats, LocalDate date) {
        List<Reservation> reservations = reservationDbService.findAllByTableIdsAndReservationDate(regularSeats.keySet(), date);

        return reservations.stream()
                .collect(Collectors.toMap(
                        reservation -> reservation.getId().getTableId(),
                        Reservation::getClientId
                ));
    }
}
