package ru.overcode.booking.mapper.officefloor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.overcode.booking.config.mapper.MappersConfig;
import ru.overcode.booking.dto.officefloor.OfficeFloorResponse;
import ru.overcode.booking.entity.officefloor.OfficeFloor;

import java.util.List;

@Mapper(config = MappersConfig.class)
public interface OfficeFloorMapper {
    @Mapping(target = "officeFloorId", source = "id")
    @Mapping(target = "floor", source = "floorNumber")
    OfficeFloorResponse toOfficeFloorResponse(OfficeFloor officeFloor);

    default List<OfficeFloorResponse> toOfficeFloorResponse(List<OfficeFloor> officeFloors) {
        return officeFloors.stream()
                .map(this::toOfficeFloorResponse)
                .toList();
    }
}
