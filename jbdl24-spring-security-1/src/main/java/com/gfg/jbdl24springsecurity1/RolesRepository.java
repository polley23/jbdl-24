package com.gfg.jbdl24springsecurity1;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolesRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByRole(String role);
}
