package com.gfg.jbdl24springsecurity1;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String role;
}
