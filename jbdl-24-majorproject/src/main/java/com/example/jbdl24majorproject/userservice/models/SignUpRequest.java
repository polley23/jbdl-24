package com.example.jbdl24majorproject.userservice.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
