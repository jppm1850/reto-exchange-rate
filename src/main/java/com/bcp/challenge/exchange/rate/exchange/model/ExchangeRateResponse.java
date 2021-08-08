package com.bcp.challenge.exchange.rate.exchange.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

    private BigDecimal amount;
    private BigDecimal amountChanged;
    private String currencyOrigin;
    private String currencyDestiny;
}
