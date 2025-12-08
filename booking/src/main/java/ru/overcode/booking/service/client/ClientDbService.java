package ru.overcode.booking.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.overcode.booking.entity.client.Client;
import ru.overcode.booking.repository.client.ClientRepository;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientDbService {

    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Map<Long, Client> findAllClientsByIds(Collection<Long> clientIds) {
        return clientRepository.findAllById(clientIds).stream()
                .collect(Collectors.toMap(
                        Client::getId,
                        Function.identity()
                ));
    }
}
