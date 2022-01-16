package com.gfg.jbdl24springsecurity1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onboard(final UserRequest userReq) {
        Optional<Role> roleOptional = rolesRepository.findByRole(userReq.getRole());

        User user = User.builder().password(passwordEncoder.encode(userReq.getPassword()))
                .username(userReq.getUsername()).roles(roleOptional.isPresent() ?
                        Arrays.asList(roleOptional.get()) :
                        Arrays.asList(new Role(userReq.getRole()))).build();
        userRepository.save(user);
    }
}
