package com.gfg.jbdl24springsecurity1.controller;
import com.gfg.jbdl24springsecurity1.UserRequest;
import com.gfg.jbdl24springsecurity1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Application {

    @Autowired
    private UserService userService;

    @GetMapping("/secured/home")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    String home(){
        return "welcome home!";
    }

    @GetMapping("/guest")
    String guest(){
        return "welcome guest!";
    }

    @GetMapping("/secured/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    String admin(){
        return "welcome admin!";
    }

    @PostMapping("/secured/user")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void onboard(@RequestBody UserRequest userRequest){
        userService.onboard(userRequest);
    }
}
