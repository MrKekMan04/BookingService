package ru.overcode.booking.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.reservation.FreeFixedReservation;
import ru.overcode.booking.entity.reservation.ReservationId;

@Repository
public interface FreeFixedReservationRepository extends JpaRepository<FreeFixedReservation, ReservationId> {
}
