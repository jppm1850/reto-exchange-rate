package com.bcp.challenge.exchange.rate.login.dao.repository;

import com.bcp.challenge.exchange.rate.login.dao.entity.User;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import java.util.UUID;

public interface UserRepository extends RxJava3CrudRepository<User, UUID> {

    @Query("SELECT * FROM users WHERE user_name = :username")
    Maybe<User> findByUserName(String username);
}
