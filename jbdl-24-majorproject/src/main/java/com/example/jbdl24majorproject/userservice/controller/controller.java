package com.example.jbdl24majorproject.userservice.controller;
import com.example.jbdl24majorproject.userservice.manager.UserManager;
import com.example.jbdl24majorproject.userservice.models.SignUpRequest;
import com.example.jbdl24majorproject.userservice.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class controller {

    @Autowired
    UserManager userManager;

    @PostMapping("/user")
    ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest){
        userManager.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{user_id}")
    ResponseEntity get(@PathVariable("user_id") String userId) throws Exception {
        UserResponse userResponse = userManager.get(userId);
        return ResponseEntity.ok(userResponse);
    }
}
