package com.example.jbdl24majorproject.transaction.controller;
import com.example.jbdl24majorproject.transaction.manager.TransactionManager;
import com.example.jbdl24majorproject.transaction.models.TransactionRequest;
import com.example.jbdl24majorproject.transaction.models.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionManager transactionManager;

    @PostMapping("/transaction")
    ResponseEntity create(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponse = transactionManager.create(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);

    }

    @GetMapping("/transaction/{tx_id}")
    ResponseEntity get(@PathVariable("tx_id") String transactionId){
        try {
            TransactionResponse transactionResponse = transactionManager.get(transactionId);
            return ResponseEntity.ok(transactionResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
