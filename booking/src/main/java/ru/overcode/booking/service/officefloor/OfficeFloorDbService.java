package ru.overcode.booking.service.officefloor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.officefloor.OfficeFloor;
import ru.overcode.booking.repository.officefloor.OfficeFloorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeFloorDbService {

    private final OfficeFloorRepository officeFloorRepository;

    @Transactional(readOnly = true)
    public List<OfficeFloor> getAllOfficesAndFloors() {
        return officeFloorRepository.findAll();
    }
}
