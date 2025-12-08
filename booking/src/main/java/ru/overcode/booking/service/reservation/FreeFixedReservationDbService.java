package ru.overcode.booking.service.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.repository.reservation.FreeFixedReservationRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FreeFixedReservationDbService {

    private final FreeFixedReservationRepository freeFixedReservationRepository;

    @Transactional(readOnly = true)
    public Set<Long> findAllByTableIdsAndReservationDate(Collection<Long> seats, LocalDate date) {
        return freeFixedReservationRepository.findAllByTableIdsAndReservationDate(seats, date);
    }
}
