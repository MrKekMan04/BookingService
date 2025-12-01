package ru.overcode.booking.repository.officefloor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.officefloor.OfficeFloor;

@Repository
public interface OfficeFloorRepository extends JpaRepository<OfficeFloor, Long> {
}
