package com.gfg.jdbl24minorproject2.controller;
import com.gfg.jdbl24minorproject2.entities.Employee;
import com.gfg.jdbl24minorproject2.manager.EmployeeService;
import com.gfg.jdbl24minorproject2.model.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    @PreAuthorize("hasAnyAuthority('ADMIN','HR')")
    void add(@RequestBody EmployeeRequest employeeRequest){
        employeeService.add(employeeRequest);
    }

    @PutMapping("/employee/{emp_id}/{manager_id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','HR')")
    void assignManager(@PathVariable("emp_id") String empId,
            @PathVariable("manager_id") String managerId){
        try {
            employeeService.assignManager(empId,managerId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @PostMapping("/employee/{emp_id}/rating/{rating}")
    @PreAuthorize("hasAnyAuthority('ADMIN','HR','MANAGER')")
    ResponseEntity addRating(@PathVariable("emp_id") String empId,
                             @PathVariable("rating") Integer rating,
                             Authentication authentication){
        try {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authentication.getAuthorities().iterator().next().toString());
           if(authority.getAuthority()=="MANAGER") {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                       = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                       authentication.getAuthorities());
               List<Employee> subOrdinates = employeeService
                       .getSubOrdinates(usernamePasswordAuthenticationToken.getName());
               for (Employee sub : subOrdinates) {
                   if (sub.getEmployeeId().equalsIgnoreCase(empId)) {
                       employeeService.addRating(empId, rating);
                       return ResponseEntity.ok().build();
                   }
               }
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
           }else{
               employeeService.addRating(empId, rating);
               return ResponseEntity.ok().build();
           }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    /*
    req1.  update rating for an emplyee - omly hr, admin can do. Manager should be able to update rating for his own suboridnates
    req2. get details
    employee will get detailed view only for themselves
    manager will get detailed view only for subordinates
    hr, admin will get all views
    everyone will get simple view.

     */

}
