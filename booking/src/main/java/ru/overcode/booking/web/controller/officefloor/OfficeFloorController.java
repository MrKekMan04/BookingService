package ru.overcode.booking.web.controller.officefloor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import ru.overcode.booking.dto.officefloor.OfficeFloorResponse;
import ru.overcode.booking.service.officefloor.OfficeFloorService;
import ru.overcode.booking.web.ApiV1;
import ru.overcode.booking.web.response.ListResponse;

@ApiV1
@RequiredArgsConstructor
public class OfficeFloorController {

    private final OfficeFloorService officeFloorService;

    @GetMapping("/offices")
    public ListResponse<OfficeFloorResponse> getOfficesAndFloors() {
        return ListResponse.success(officeFloorService.getAllOfficesAndFloors());
    }
}
