package com.gfg.jdbl24minorproject2.Repository;
import com.gfg.jdbl24minorproject2.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository<T extends Employee>  extends CrudRepository<T,Long> {
    List<T> findAllByFirstNameAndLastName(String firstname, String lastname);
    Optional<T> findByEmployeeId(String id);
}
