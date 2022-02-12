package com.example.jbdl24majorproject.wallet.manager;


public interface WalletManager {
    public void updateOnTransaction(String transactionEventJson);
    public void createOnUserCreation(String userId);
}
