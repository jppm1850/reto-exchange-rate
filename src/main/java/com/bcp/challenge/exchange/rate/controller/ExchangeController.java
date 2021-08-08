package com.bcp.challenge.exchange.rate.controller;


import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateRequest;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateResponse;
import com.bcp.challenge.exchange.rate.exchange.business.ExchangeService;
import io.reactivex.rxjava3.core.Maybe;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

@RestController
@RequestMapping("/challenge/bcp/v1/exchange-rate")
@Tag(name = "Exchange Rate", description = "Exchange rate")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/exchange")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<ExchangeRateResponse> getExchangeRate(@RequestBody ExchangeRateRequest request) {

        return exchangeService.getExchange(request);
    }
}