package com.bcp.challenge.exchange.rate.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security Filter.
     *
     * @param http {@link ServerHttpSecurity}
     * @return SecurityWebFilterChain
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                )
                .and()
                .logout().disable()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/challenge/bcp/v1/exchange-rate/*").permitAll()
                .pathMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                .pathMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .pathMatchers(HttpMethod.GET, "/actuator").permitAll()
                .pathMatchers(HttpMethod.GET, "/actuator/*").permitAll()
                .pathMatchers(HttpMethod.GET, "/actuator/health/%7B*path%7D").permitAll()
                .anyExchange().denyAll()
                .and().build();
    }

}
