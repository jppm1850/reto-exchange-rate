package com.bcp.challenge.exchange.rate.login.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {

    private String userName;
    private String password;
}
