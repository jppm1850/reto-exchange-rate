package com.bcp.challenge.exchange.rate.exchange.dao.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;


@Table("exchange_rate")
@Getter
@Setter
@Builder
public class ExchangeRate {
    @Id
    private UUID id;
    @Column("currency_origin")
    private String currencyOrigin;
    @Column("currency_destiny")
    private String currencyDestiny;
    @Column("exchange_amount")
    private BigDecimal exchangeAmount;
}
