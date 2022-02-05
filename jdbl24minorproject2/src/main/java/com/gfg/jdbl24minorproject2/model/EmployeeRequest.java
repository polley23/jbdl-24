package com.gfg.jdbl24minorproject2.model;
import com.gfg.jdbl24minorproject2.entities.Roles;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Roles role;
}
