package com.example.jbdl24majorproject.transaction.repositories;
import com.example.jbdl24majorproject.transaction.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    Optional<Transaction> findByTxId(String id);

}
