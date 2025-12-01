package ru.overcode.booking.repository.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.seat.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
