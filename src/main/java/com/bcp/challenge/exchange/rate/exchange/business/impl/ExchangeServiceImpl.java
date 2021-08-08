package com.bcp.challenge.exchange.rate.exchange.business.impl;

import com.bcp.challenge.exchange.rate.common.ApiException;
import com.bcp.challenge.exchange.rate.exchange.dao.repository.ExchangeRateRepository;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateRequest;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateResponse;
import com.bcp.challenge.exchange.rate.exchange.business.ExchangeService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRateRepository repository;

    public Maybe<ExchangeRateResponse> getExchange(ExchangeRateRequest request) {

        return repository.findExchange(request.getCurrencyOrigin(), request.getCurrencyDestiny())
                .switchIfEmpty(Maybe.error(ApiException.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .description("No se encontro una moneda para este tipo de cambio")
                        .build()))
                .map(exchange -> request.getAmount().divide(exchange.getExchangeAmount(), 4, RoundingMode.HALF_UP))
                .map(amount -> ExchangeRateResponse.builder()
                        .amount(request.getAmount())
                        .amountChanged(amount)
                        .currencyDestiny(request.getCurrencyDestiny())
                        .currencyOrigin(request.getCurrencyOrigin())
                        .build()
                );

    }
}
