package ru.overcode.booking.service.seat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.seat.Seat;
import ru.overcode.booking.repository.seat.SeatRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatDbService {

    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Map<Boolean, Map<Long, Seat>> getSeatsForOfficeFloor(Long officeFloorId) {
        return seatRepository.findAllByOfficeFloorId(officeFloorId).stream()
                .collect(Collectors.partitioningBy(
                        seat -> seat.getFixedClientId() != null,
                        Collectors.toMap(Seat::getId, Function.identity())
                ));
    }

    @Transactional(readOnly = true)
    public Optional<Seat> findById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    @Transactional(readOnly = true)
    public Optional<Seat> findClientFixedOfficeSeat(Long clientId, Collection<Long> officeFloorIds) {
        return seatRepository.findByFixedClientIdAndOfficeFloorIdIn(clientId, officeFloorIds);
    }
}
