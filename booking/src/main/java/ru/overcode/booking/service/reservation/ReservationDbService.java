package ru.overcode.booking.service.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.repository.reservation.ReservationRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationDbService {

    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> findAllByTableIdsAndReservationDate(Collection<Long> seatIds, LocalDate date) {
        return reservationRepository.findAllByTableIdsAndReservationDate(seatIds, date);
    }

    @Transactional(readOnly = true)
    public Optional<Reservation> findByClientIdOnDate(Long clientId, LocalDate date) {
        return reservationRepository.findByIdReservationDateAndClientId(date, clientId);
    }

    @Transactional
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
