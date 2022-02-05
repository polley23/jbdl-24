package com.gfg.jdbl24minorproject2.model;
        import lombok.*;
        import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String email;
}
