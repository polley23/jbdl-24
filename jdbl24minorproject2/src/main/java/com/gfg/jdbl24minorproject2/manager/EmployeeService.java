package com.gfg.jdbl24minorproject2.manager;
import com.gfg.jdbl24minorproject2.entities.Employee;
import com.gfg.jdbl24minorproject2.model.EmployeeRequest;
import com.gfg.jdbl24minorproject2.model.EmployeeResponse;
import com.gfg.jdbl24minorproject2.model.EmployeeResponseWithRating;

import java.util.List;

public interface EmployeeService {
    void add(EmployeeRequest employeeRequest);
    List<EmployeeResponseWithRating> get(String firstName, String lastName, Boolean detailed);
    void addRating(String employeeId, Integer rating) throws Throwable;
    List<Employee> getSubOrdinates(String managerId) throws Throwable;
    public void assignManager(String empId, String managerId) throws Throwable;
}
