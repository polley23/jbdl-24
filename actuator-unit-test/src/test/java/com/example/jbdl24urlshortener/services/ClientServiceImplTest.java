package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.entity.Client;
import com.example.jbdl24urlshortener.model.ClientRequest;
import com.example.jbdl24urlshortener.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImplTest {
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    ClientRequest clientRequest;

    Client client;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImpl(clientRepository);
        clientRequest = ClientRequest.builder().host("abc").port("9090").expireAfterMillis(1000l).name("name").build();
        client = Client.builder().host("abc").port("9090").expireAfterMillis(1000l).name("name").build();
    }

    @Test
    void onboardClient() {
        clientService.onboardClient(clientRequest);
        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(null);

    }

    @Test
    void updateClient() {
        clientService.updateClient(client);

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(null);

    }

    @Test
    void getClient() {
        Mockito.when(clientRepository.findByName(Mockito.any())).thenReturn(Optional.of(client));
        Client result = clientService.getClient("name").get();
        Assertions.assertEquals(client.getHost(),result.getHost());

    }
}