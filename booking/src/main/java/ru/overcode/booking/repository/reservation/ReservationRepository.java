package ru.overcode.booking.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.entity.reservation.ReservationId;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
