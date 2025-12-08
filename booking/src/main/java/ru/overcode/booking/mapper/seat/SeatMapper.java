package ru.overcode.booking.mapper.seat;

import org.mapstruct.Mapper;
import ru.overcode.booking.config.mapper.MappersConfig;
import ru.overcode.booking.dto.seat.BookedClientDto;
import ru.overcode.booking.dto.seat.SeatOnOfficeFloorResponse;
import ru.overcode.booking.entity.client.Client;
import ru.overcode.booking.entity.seat.Seat;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper(config = MappersConfig.class)
public interface SeatMapper {

    default List<SeatOnOfficeFloorResponse> toSeatOnOfficeFloorResponse(
            Map<Boolean, Map<Long, Seat>> seats,
            Map<Long, Long> clientIdsByTableIds,
            Map<Long, Client> clientsById
    ) {
        return seats.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .map(seat -> new SeatOnOfficeFloorResponse(
                        seat.getId(),
                        Optional.of(seat.getId())
                                .map(clientIdsByTableIds::get)
                                .map(clientsById::get)
                                .map(client -> new BookedClientDto(client.getFirstName(), client.getLastName()))
                                .orElse(null),
                        seat.getXPosition(),
                        seat.getYPosition(),
                        seat.getRotation()
                ))
                .toList();
    }
}
