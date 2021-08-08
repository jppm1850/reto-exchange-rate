package com.bcp.challenge.exchange.rate.config;

import com.bcp.challenge.exchange.rate.common.ApiException;
import com.bcp.challenge.exchange.rate.common.JWTUtil;
import com.bcp.challenge.exchange.rate.common.ExcludeEnpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorizatioInterceptor implements WebFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        if (isExcludedEnpoint(exchange.getRequest())) {
            return chain.filter(exchange);
        }
        return validateTokenJWT(exchange, chain);
    }

    private Mono<Void> validateTokenJWT(ServerWebExchange exchange, WebFilterChain chain) {

        List<String> headerAuth = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

        if(headerAuth == null){
            return Mono.error(ApiException.builder()
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .description("Necesita un token JWT")
                    .build());
        }

        String authToken = headerAuth.get(0).substring(7);

        return Mono.just(jwtUtil.validateToken(authToken))
                .flatMap(valid -> {
                    if (!valid) {
                        return Mono.error(ApiException.builder()
                                .statusCode(HttpStatus.UNAUTHORIZED.value())
                                .description("Error token Incorrecto")
                                .build());
                    }
                    return chain.filter(exchange);
                })
                .onErrorResume(ex->Mono.error(ApiException.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .description("Ocurrio Algun error al validar el token")
                        .build()));

    }

    private boolean isExcludedEnpoint(ServerHttpRequest request) {
        PathContainer path = request.getPath().pathWithinApplication();

        Optional<ExcludeEnpoints> optionalEndpoint = Arrays.stream(ExcludeEnpoints.values())
                .filter(endpoint -> endpoint.getPattern().matches(path)
                        && endpoint.getHttpMethod() == request.getMethod())
                .findAny();

        return optionalEndpoint.isPresent();
    }

}
