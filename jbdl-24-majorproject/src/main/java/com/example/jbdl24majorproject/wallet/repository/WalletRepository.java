package com.example.jbdl24majorproject.wallet.repository;
import com.example.jbdl24majorproject.wallet.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findByUserId(String userId);
}
