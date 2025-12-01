package ru.overcode.booking.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.overcode.booking.entity.client.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
