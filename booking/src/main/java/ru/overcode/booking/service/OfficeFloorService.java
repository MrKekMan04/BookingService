package ru.overcode.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.dto.officefloor.OfficeFloorResponse;
import ru.overcode.booking.entity.officefloor.OfficeFloor;
import ru.overcode.booking.mapper.officefloor.OfficeFloorMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeFloorService {

    private final OfficeFloorDbService officeFloorDbService;
    private final OfficeFloorMapper officeFloorMapper;

    @Transactional(readOnly = true)
    public List<OfficeFloorResponse> getAllOfficesAndFloors() {
        List<OfficeFloor> officesFloors = officeFloorDbService.getAllOfficesAndFloors();

        return officeFloorMapper.toOfficeFloorResponse(officesFloors);
    }
}
