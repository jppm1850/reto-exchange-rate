package com.bcp.challenge.exchange.rate.exchange.business;

import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateRequest;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface ExchangeService {

    Maybe<ExchangeRateResponse> getExchange(ExchangeRateRequest request);
}
