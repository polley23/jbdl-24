package com.example.jbdl24majorproject.wallet.models;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WalletEvent {
    private String txId;
    private WalletStatus walletStatus;
}
