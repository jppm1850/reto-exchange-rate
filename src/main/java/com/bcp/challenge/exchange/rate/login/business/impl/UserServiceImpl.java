package com.bcp.challenge.exchange.rate.login.business.impl;


import com.bcp.challenge.exchange.rate.common.ApiException;
import com.bcp.challenge.exchange.rate.common.JWTUtil;
import com.bcp.challenge.exchange.rate.login.dao.repository.UserRepository;
import com.bcp.challenge.exchange.rate.login.enums.Role;
import com.bcp.challenge.exchange.rate.login.model.UserDetail;
import com.bcp.challenge.exchange.rate.login.model.AuthRequest;
import com.bcp.challenge.exchange.rate.login.model.AuthResponse;
import com.bcp.challenge.exchange.rate.login.business.UserService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Maybe<AuthResponse> generateLogin(AuthRequest request) {

        return userRepository.findByUserName(request.getUserName())
                .switchIfEmpty(Maybe.error(ApiException.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .description("Usuario o password invalido")
                        .build()))
                .map(user -> new UserDetail(user.getUserName(), user.getPassword(), user.getEnabled(), Arrays.asList(Role.valueOf(user.getRol()))))
                .filter(userDetails -> passwordEncoder.matches(request.getPassword(), userDetails.getPassword()))
                .map(userDetails -> jwtUtil.generateToken(userDetails))
                .map(token -> AuthResponse.builder().token(token).build());


    }
}
