package com.bcp.challenge.exchange.rate.common;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

@Getter
public enum ExcludeEnpoints {

    SWAGGER_ENDPOINT("/swagger-ui.html", HttpMethod.GET),
    OPEN_API("/v3/api-docs", HttpMethod.GET),
    ACTUATOR_ENDPOINT3("/actuator/health/%7B*path%7D", HttpMethod.GET),
    ACTUATOR_ENDPOINT2("/actuator/*", HttpMethod.GET),
    ACTUATOR_ENDPOINT("/actuator", HttpMethod.GET),
    LOGIN_ENDPOINT("/challenge/bcp/v1/exchange-rate/login", HttpMethod.POST);

    private final PathPattern pattern;
    private final HttpMethod httpMethod;

    ExcludeEnpoints(String path, HttpMethod httpMethod) {
        this.pattern = new PathPatternParser().parse(path);
        this.httpMethod = httpMethod;
    }
}
