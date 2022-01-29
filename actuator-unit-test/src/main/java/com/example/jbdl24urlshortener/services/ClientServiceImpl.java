package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.entity.Client;
import com.example.jbdl24urlshortener.model.ClientRequest;
import com.example.jbdl24urlshortener.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public void onboardClient(final ClientRequest clientRequest) {
        Client client = Client.builder().host(clientRequest.getHost())
                .name(clientRequest.getName())
                .port(clientRequest.getPort())
                .expireAfterMillis(clientRequest.getExpireAfterMillis())
                .build();
        clientRepository.save(client);
    }

    @Override
    public void updateClient(final Client client) {
        clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClient(final String name) {
        return clientRepository.findByName(name);
    }
}
