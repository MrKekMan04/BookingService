package ru.overcode.booking.service.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.repository.reservation.ReservationRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDbService {

    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> findAllByTableIdsAndReservationDate(Collection<Long> seatIds, LocalDate date) {
        return reservationRepository.findAllByTableIdsAndReservationDate(seatIds, date);
    }
}
