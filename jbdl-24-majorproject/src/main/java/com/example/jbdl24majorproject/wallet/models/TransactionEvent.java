package com.example.jbdl24majorproject.wallet.models;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionEvent {
    private String txId;
    private String fromUser;
    private String toUser;
    private Float amount;
    private TransactionStatus status;
}
