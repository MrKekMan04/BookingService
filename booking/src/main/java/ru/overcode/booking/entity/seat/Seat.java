package ru.overcode.booking.entity.seat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "seat")
public class Seat {

    @Id
    private Long id;

    private Long officeFloorId;

    private Long fixedClientId;

    private BigDecimal xPosition;

    private BigDecimal yPosition;

    private Integer rotation;
}
