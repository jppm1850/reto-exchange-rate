
package com.bcp.challenge.exchange.rate.controller;

import com.bcp.challenge.exchange.rate.login.model.AuthRequest;
import com.bcp.challenge.exchange.rate.login.model.AuthResponse;
import com.bcp.challenge.exchange.rate.login.business.UserService;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge/bcp/v1/exchange-rate")
@Tag(name = "Login", description = "Login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<AuthResponse> login(@RequestBody AuthRequest request) {

        return userService.generateLogin(request);

    }

}

