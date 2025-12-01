package ru.overcode.booking.entity.reservation;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "free_fixed_reservation")
public class FreeFixedReservation {

    @EmbeddedId
    private ReservationId id;
}
