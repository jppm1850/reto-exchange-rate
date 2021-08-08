package com.bcp.challenge.exchange.rate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Passsword {


    @Test
    public void password(){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
