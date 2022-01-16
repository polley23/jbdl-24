package com.gfg.jbdl24springsecurity1.configuration;

import com.gfg.jbdl24springsecurity1.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    @Qualifier("userDetailService")
    UserDetailsService userDetailsServiceImpl;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder())
                .and()
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("admin")
                .password(passwordEncoder().encode("password"))
                .authorities("ADMIN");

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .httpBasic()
        .and()
        .csrf()
        .disable();
    }

    @Override
    public void configure(final WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/guest");
    }
}
