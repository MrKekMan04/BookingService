package ru.overcode.booking.entity.reservation;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class ReservationId implements Serializable {

    private LocalDate reservationDate;

    private Long tableId;
}



