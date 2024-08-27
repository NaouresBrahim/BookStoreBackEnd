package com.example.bookstore;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@TestConfiguration
public class TestSecurityConfig  {

//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests().anyRequest().permitAll();
//    }
}