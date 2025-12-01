package ru.overcode.booking.entity.officefloor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "office_floor")
public class OfficeFloor {

    @Id
    private Long id;

    private String officeName;

    private Integer floorNumber;
}
