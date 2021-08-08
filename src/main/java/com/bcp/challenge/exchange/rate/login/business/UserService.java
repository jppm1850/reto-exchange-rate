package com.bcp.challenge.exchange.rate.login.business;

import com.bcp.challenge.exchange.rate.login.model.AuthRequest;
import com.bcp.challenge.exchange.rate.login.model.AuthResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface UserService {

    public Maybe<AuthResponse> generateLogin(AuthRequest username);
}
