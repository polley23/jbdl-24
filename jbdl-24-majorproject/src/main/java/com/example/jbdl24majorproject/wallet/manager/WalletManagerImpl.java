package com.example.jbdl24majorproject.wallet.manager;
import com.example.jbdl24majorproject.transaction.models.TransactionEvent;
import com.example.jbdl24majorproject.wallet.entity.Wallet;
import com.example.jbdl24majorproject.wallet.models.WalletEvent;
import com.example.jbdl24majorproject.wallet.models.WalletStatus;
import com.example.jbdl24majorproject.wallet.repository.WalletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WalletManagerImpl implements WalletManager
{
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    @KafkaListener(topics = "transaction", groupId = "wallet")
    public void updateOnTransaction(final String transactionEventJson)   {
        TransactionEvent transactionEvent = null;
        try {
            transactionEvent = objectMapper.readValue(transactionEventJson,TransactionEvent.class);
            Wallet walletFrom = walletRepository.findByUserId(transactionEvent.getFromUser())
                    .orElseThrow(()-> new Exception("wallet not found"));
            Wallet walletTo = walletRepository.findByUserId(transactionEvent.getToUser())
                    .orElseThrow(()-> new Exception("wallet not found"));

            if(walletFrom.getAmount() < transactionEvent.getAmount()){
                WalletEvent walletEvent = WalletEvent.builder().txId(transactionEvent.getTxId()).walletStatus(WalletStatus.REJECTED).build();
                kafkaTemplate.send("wallet",objectMapper.writeValueAsString(walletEvent));
            }
            walletFrom.setAmount(walletFrom.getAmount()-transactionEvent.getAmount());
            walletTo.setAmount(walletTo.getAmount()+transactionEvent.getAmount());
            walletRepository.saveAll(Arrays.asList(walletFrom,walletTo));
            WalletEvent walletEvent = WalletEvent.builder().txId(transactionEvent.getTxId()).walletStatus(WalletStatus.APPROVED).build();
            kafkaTemplate.send("wallet",objectMapper.writeValueAsString(walletEvent));


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            WalletEvent walletEvent = WalletEvent.builder().txId(transactionEvent.getTxId()).walletStatus(WalletStatus.REJECTED).build();
            try {
                kafkaTemplate.send("wallet",objectMapper.writeValueAsString(walletEvent));
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    @KafkaListener(topics = "user", groupId = "wallet")
    public void createOnUserCreation(final String userId) {
        Wallet wallet = Wallet.builder().amount(1000f).userId(userId).build();
        walletRepository.save(wallet);
    }
}
