package com.example.jbdl24majorproject.transaction.manager;
import com.example.jbdl24majorproject.transaction.entities.Transaction;
import com.example.jbdl24majorproject.transaction.models.*;
import com.example.jbdl24majorproject.transaction.repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionManagerImpl implements TransactionManager{

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public TransactionResponse create(final TransactionRequest transactionRequest) {
        Transaction transaction =  Transaction.builder().amount(transactionRequest.getAmount())
                .fromUser(transactionRequest.getFrom())
                .toUser(transactionRequest.getTo())
                .status(TransactionStatus.PENDING)
                .txId(UUID.randomUUID().toString())
                .date(new Date())
                .build();
        transactionRepository.save(transaction);
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .status(transaction.getStatus())
                .txId(transaction.getTxId())
                .build();
        TransactionEvent transactionEvent = TransactionEvent.builder().amount(transaction.getAmount())
                .fromUser(transaction.getFromUser()).toUser(transaction.getToUser()).status(transaction.getStatus()).txId(transaction.getTxId()).build();
        try {
            kafkaTemplate.send("transaction", objectMapper.writeValueAsString(transactionEvent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    @Override
    public TransactionResponse get(final String transactionId) throws Exception {
        Transaction transaction = transactionRepository.findByTxId(transactionId).orElseThrow(()->
                new Exception("tx id not present"));
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .status(transaction.getStatus())
                .txId(transaction.getTxId())
                .build();

        return transactionResponse;
    }

    @Override
    @KafkaListener(topics = "wallet", groupId = "transaction")
    public void update(final String request) {
        WalletEvent walletEvent = null;
        try {
            walletEvent = objectMapper.readValue(request, WalletEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Transaction transaction = transactionRepository.findByTxId(walletEvent.getTxId()).get();

        if(walletEvent.getWalletStatus().name().equals(WalletStatus.APPROVED.name())){
            transaction.setStatus(TransactionStatus.APPROVED);
        }else{
            transaction.setStatus(TransactionStatus.REJECTED);

        }
        TransactionEvent transactionEvent = TransactionEvent.builder().amount(transaction.getAmount())
                .fromUser(transaction.getFromUser()).toUser(transaction.getToUser()).status(transaction.getStatus()).txId(transaction.getTxId()).build();
        try {
            kafkaTemplate.send("transaction-complete", objectMapper.writeValueAsString(transactionEvent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        transactionRepository.save(transaction);
    }
}
