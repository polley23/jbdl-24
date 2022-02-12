package com.example.jbdl24majorproject.userservice.manager;
import com.example.jbdl24majorproject.userservice.entities.User;
import com.example.jbdl24majorproject.userservice.models.SignUpRequest;
import com.example.jbdl24majorproject.userservice.models.UserResponse;
import com.example.jbdl24majorproject.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public void signUp(final SignUpRequest signUpRequest) {
        User user = User.builder().email(signUpRequest.getEmail()).password(signUpRequest.getPassword())
                .username(signUpRequest.getUsername()).build();
        userRepository.save(user);
        kafkaTemplate.send("user",user.getUsername());
    }

    @Override
    public UserResponse get(final String username) throws Exception {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new Exception("username not found"));
        UserResponse userResponse = UserResponse.builder().email(user.getEmail()).password(user.getPassword())
                .username(user.getUsername()).build();
        return userResponse;
    }
}
