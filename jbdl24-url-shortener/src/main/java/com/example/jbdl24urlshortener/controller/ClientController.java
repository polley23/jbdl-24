package com.example.jbdl24urlshortener.controller;
import com.example.jbdl24urlshortener.model.ClientRequest;
import com.example.jbdl24urlshortener.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("v1/client")
    void onbaord(@RequestBody ClientRequest clientRequest){
        clientService.onboardClient(clientRequest);

    }
}
