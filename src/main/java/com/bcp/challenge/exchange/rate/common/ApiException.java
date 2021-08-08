package com.bcp.challenge.exchange.rate.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiException extends RuntimeException{

    private final String description;
    private final int statusCode;
}
