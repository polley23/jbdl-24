package com.gfg.jdbl24minorproject2.manager;
import com.gfg.jdbl24minorproject2.Repository.EmployeeRepository;
import com.gfg.jdbl24minorproject2.Repository.UserRepository;
import com.gfg.jdbl24minorproject2.entities.*;
import com.gfg.jdbl24minorproject2.model.EmployeeRequest;
import com.gfg.jdbl24minorproject2.model.EmployeeResponseWithRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("userDetailsService")
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void add(final EmployeeRequest employeeRequest) {
        User user = User.builder().username(employeeRequest.getEmail())
                .password(passwordEncoder.encode(employeeRequest.getLastName()))
                .role(employeeRequest.getRole()).build();
        switch (employeeRequest.getRole()
        ){
            case HR:
                Hr hr= Hr
                        .builder().employeeId(employeeRequest.getEmail())
                        .firstName(employeeRequest.getFirstName())
                        .lastName(employeeRequest.getLastName())
                        .email(employeeRequest.getEmail())
                        .user(user).build();
                employeeRepository.save(hr);
                break;
            case MANAGER:
                Manager manager= Manager
                        .builder().employeeId(employeeRequest.getEmail())
                        .firstName(employeeRequest.getFirstName())
                        .lastName(employeeRequest.getLastName())
                        .email(employeeRequest.getEmail())
                        .user(user).build();
                employeeRepository.save(manager);
                break;
            case EMPLOYEE:
                Employee employee= Employee
                        .builder().employeeId(employeeRequest.getEmail())
                        .firstName(employeeRequest.getFirstName())
                        .lastName(employeeRequest.getLastName())
                        .email(employeeRequest.getEmail())
                        .user(user).build();
                employeeRepository.save(employee);
        }

    }

    @Override
    public List<EmployeeResponseWithRating> get(final String firstName,
                                                final String lastName,
                                                final Boolean detailed) {
        List<Employee> employees = employeeRepository.findAllByFirstNameAndLastName(firstName,lastName);
        List<EmployeeResponseWithRating> employeeResponseWithRatings = new ArrayList<>();
        if(detailed){
            for(Employee employee :employees){
                employeeResponseWithRatings.add(EmployeeResponseWithRating.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                        .rating(employee.getRatings().get(employee.getRatings().size()-1).getRating()
                        )
                .build());
            }
        }else {
            for(Employee employee :employees){
                employeeResponseWithRatings.add(EmployeeResponseWithRating.builder()
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .build());
            }
        }

        return employeeResponseWithRatings;

    }

    @Override
    public void addRating(final String employeeId, final Integer rating) throws Throwable {
        Employee employee = (Employee) employeeRepository.findByEmployeeId(employeeId).orElseThrow(()-> new UsernameNotFoundException("employee not present"));
        if(employee.getRatings() == null ){
            employee.setRatings(Arrays.asList( Rating.builder().rating(rating).createdTimestamp(Date.valueOf(LocalDateTime.now().toLocalDate())).build()));
        }
        employee.getRatings().add(Rating.builder().rating(rating).createdTimestamp(Date.valueOf(LocalDateTime.now().toLocalDate())).build());
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getSubOrdinates(final String managerId) throws Throwable {

        Manager manager = (Manager) employeeRepository.findByEmployeeId(managerId).orElseThrow(()-> new  UsernameNotFoundException("employee not present"));

        return manager.getSubordinates();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new  UsernameNotFoundException("employee not present") );
        return user;
    }

    @Override
    public void assignManager(String empId, String managerId) throws Throwable {
        Employee employee = (Employee) employeeRepository.findByEmployeeId(empId).orElseThrow(()-> new UsernameNotFoundException("employee not present"));
        Manager manager = (Manager) employeeRepository.findByEmployeeId(managerId).orElseThrow(()-> new UsernameNotFoundException("employee not present"));
        if(manager.getSubordinates()==null){
            manager.setSubordinates(new ArrayList<>());
        }
        manager.getSubordinates().add(employee);
        employeeRepository.save(manager);


    }
}
