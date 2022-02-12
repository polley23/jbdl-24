package com.example.jbdl24majorproject.userservice.repository;
import com.example.jbdl24majorproject.userservice.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
