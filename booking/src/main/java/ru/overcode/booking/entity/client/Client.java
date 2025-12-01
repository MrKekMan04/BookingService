package ru.overcode.booking.entity.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    private Long id;

    private String firstName;

    private String lastName;
}
