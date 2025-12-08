package ru.overcode.booking.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.reservation.Reservation;
import ru.overcode.booking.entity.reservation.ReservationId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {

    @Query("""
             select r
             from Reservation r
             where r.id.tableId in :seatIds
             and r.id.reservationDate = :date
            """)
    List<Reservation> findAllByTableIdsAndReservationDate(Collection<Long> seatIds, LocalDate date);
}
