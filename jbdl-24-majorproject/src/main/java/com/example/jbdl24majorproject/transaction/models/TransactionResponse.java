package com.example.jbdl24majorproject.transaction.models;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {
    private String txId;
    private TransactionStatus status;
}
