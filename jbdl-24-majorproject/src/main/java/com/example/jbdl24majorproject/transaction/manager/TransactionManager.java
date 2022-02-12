package com.example.jbdl24majorproject.transaction.manager;
import com.example.jbdl24majorproject.transaction.models.TransactionRequest;
import com.example.jbdl24majorproject.transaction.models.TransactionResponse;

public interface TransactionManager {
    TransactionResponse create(TransactionRequest transactionRequest);
    TransactionResponse  get(String transactionId) throws Exception;
    void update(String request);
}
