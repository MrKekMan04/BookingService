package ru.overcode.booking.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.reservation.FreeFixedReservation;
import ru.overcode.booking.entity.reservation.ReservationId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Repository
public interface FreeFixedReservationRepository extends JpaRepository<FreeFixedReservation, ReservationId> {

    @Query("""
             select ffr.id.tableId
             from FreeFixedReservation ffr
             where ffr.id.tableId in :seatIds
             and ffr.id.reservationDate = :date
            """)
    Set<Long> findAllByTableIdsAndReservationDate(Collection<Long> seatIds, LocalDate date);
}
