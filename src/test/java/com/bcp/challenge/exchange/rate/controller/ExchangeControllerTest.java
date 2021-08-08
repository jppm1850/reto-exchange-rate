package com.bcp.challenge.exchange.rate.controller;

import com.bcp.challenge.exchange.rate.login.model.AuthRequest;
import com.bcp.challenge.exchange.rate.login.model.AuthResponse;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateRequest;
import com.bcp.challenge.exchange.rate.exchange.model.ExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureWebTestClient(timeout = "36000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeControllerTest {

    @Autowired
    private WebTestClient client;


    @Test
    public void exchangeTestWhenIsOk(){

        AuthRequest authRequest = AuthRequest.builder()
                .userName("jpecho")
                .password("123")
                .build();

        EntityExchangeResult<AuthResponse> webTestClientLogin= client.post()
                .uri("/challenge/bcp/v1/exchange-rate/login")
                .body(Mono.just(authRequest),AuthRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AuthResponse.class)
                .returnResult();

        assertTrue(webTestClientLogin.getResponseBody().getToken()!=null, "the user not has a token JWT");

        ExchangeRateRequest request = ExchangeRateRequest.builder()
                .amount(BigDecimal.valueOf(1000))
                .currencyOrigin("PEN")
                .currencyDestiny("USD")
                .build();

        EntityExchangeResult<ExchangeRateResponse> webTestClientExchange = client.post()
                .uri("/challenge/bcp/v1/exchange-rate/exchange")
                .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(webTestClientLogin.getResponseBody().getToken()))
                .body(Mono.just(request),ExchangeRateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ExchangeRateResponse.class)
                .returnResult();

        ExchangeRateResponse exchangeRateResponse= webTestClientExchange.getResponseBody();

        assertTrue(exchangeRateResponse.getAmountChanged().equals(BigDecimal.valueOf(244.2897)), "The amount changed is incorrect");
        assertTrue(exchangeRateResponse.getAmount().equals(request.getAmount()), "The amount is incorrect");
        assertTrue(exchangeRateResponse.getCurrencyOrigin().equals(request.getCurrencyOrigin()), "The current destiny is incorrect");
        assertTrue(exchangeRateResponse.getCurrencyDestiny().equals(request.getCurrencyDestiny()), "The current origin is incorrect");

    }




}