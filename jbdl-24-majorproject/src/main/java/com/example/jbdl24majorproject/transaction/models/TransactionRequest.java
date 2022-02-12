package com.example.jbdl24majorproject.transaction.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequest {
    private String from;
    private String to;
    private Float amount;
}
