package com.example.jbdl24urlshortener.repository;
import com.example.jbdl24urlshortener.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {
    Optional<Client> findByName(String name);
}
