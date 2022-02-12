package com.example.jbdl24majorproject.userservice.models;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String username;
    private String password;
    private String email;
}
