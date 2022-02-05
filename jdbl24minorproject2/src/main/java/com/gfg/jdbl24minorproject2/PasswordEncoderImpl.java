package com.gfg.jdbl24minorproject2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @Override
    public String encode(final CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
}
