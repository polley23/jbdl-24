package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.entity.Client;
import com.example.jbdl24urlshortener.model.ClientRequest;

import java.util.Optional;

public interface ClientService {
    void onboardClient(ClientRequest clientRequest);
    void updateClient(Client  client);
    Optional<Client> getClient(String name);
}
