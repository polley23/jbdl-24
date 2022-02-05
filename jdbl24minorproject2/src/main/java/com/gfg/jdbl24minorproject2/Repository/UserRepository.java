package com.gfg.jdbl24minorproject2.Repository;
import com.gfg.jdbl24minorproject2.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
