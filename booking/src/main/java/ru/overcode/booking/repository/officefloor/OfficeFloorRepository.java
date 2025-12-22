package ru.overcode.booking.repository.officefloor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.officefloor.OfficeFloor;

import java.util.List;

@Repository
public interface OfficeFloorRepository extends JpaRepository<OfficeFloor, Long> {

    @Query("""
        select off.id from OfficeFloor off
        where off.officeName = :officeName
        """)
    List<Long> findAllByOfficeName(String officeName);
}
