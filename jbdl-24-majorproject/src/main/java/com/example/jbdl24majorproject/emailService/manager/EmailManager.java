package com.example.jbdl24majorproject.emailService.manager;
import com.example.jbdl24majorproject.transaction.models.TransactionEvent;
import com.example.jbdl24majorproject.userservice.models.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class EmailManager {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SimpleMailMessage simpleMailMessage;

    @KafkaListener(topics = "transaction-complete", groupId = "email")
    void sendTxMail(String txEventJson) throws JsonProcessingException, URISyntaxException {
        TransactionEvent transactionEvent = objectMapper.readValue(txEventJson,TransactionEvent.class);
        UserResponse userResponse =  restTemplate.getForEntity(new URI("http://localhost:8080/user/"+transactionEvent.getFromUser()),UserResponse.class
        ).getBody();
        simpleMailMessage.setTo(userResponse.getEmail());
        simpleMailMessage.setSubject("transaction update");
        simpleMailMessage.setText("Your transaction "+transactionEvent.getStatus().name());
        mailSender.send(simpleMailMessage);


    }
}
