package com.bcp.challenge.exchange.rate.exchange.dao.repository;

import com.bcp.challenge.exchange.rate.exchange.dao.entity.ExchangeRate;


import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import java.util.UUID;

public interface ExchangeRateRepository extends RxJava3CrudRepository<ExchangeRate, UUID> {

    @Query("SELECT * FROM exchange_rate WHERE currency_origin = :currencyOrigin and currency_destiny = :currencyDestiny")
    Maybe<ExchangeRate> findExchange(String currencyOrigin, String currencyDestiny);

}
