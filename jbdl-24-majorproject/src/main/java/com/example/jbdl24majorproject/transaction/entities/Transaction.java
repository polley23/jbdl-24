package com.example.jbdl24majorproject.transaction.entities;
import com.example.jbdl24majorproject.transaction.models.TransactionStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "transactions")
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txId;
    private String fromUser;
    private String toUser;
    private Float amount;
    private TransactionStatus status;
    @Temporal(value = TemporalType.DATE)
    private Date date;
}
