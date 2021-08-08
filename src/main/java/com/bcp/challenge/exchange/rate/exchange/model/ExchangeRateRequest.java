package com.bcp.challenge.exchange.rate.exchange.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ExchangeRateRequest {

    private BigDecimal amount;
    private String currencyOrigin;
    private String currencyDestiny;
}
