package ru.overcode.booking.repository.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.seat.Seat;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByOfficeFloorId(Long officeFloorId);

    Optional<Seat> findByFixedClientIdAndOfficeFloorIdIn(Long fixedClientId, Collection<Long> officeFloorIds);
}
