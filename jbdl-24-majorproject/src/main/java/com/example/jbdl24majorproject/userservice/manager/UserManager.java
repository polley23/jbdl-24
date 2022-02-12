package com.example.jbdl24majorproject.userservice.manager;
import com.example.jbdl24majorproject.userservice.models.SignUpRequest;
import com.example.jbdl24majorproject.userservice.models.UserResponse;

public interface UserManager {

    void signUp(SignUpRequest signUpRequest);
    UserResponse get(String username) throws Exception;
}
