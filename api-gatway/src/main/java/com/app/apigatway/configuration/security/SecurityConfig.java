package com.app.apigatway.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration conf = new CorsConfiguration();
            conf.addAllowedOrigin("http://localhost:4200");
            conf.addAllowedMethod("GET");
            conf.addAllowedMethod("POST");
            conf.addAllowedMethod("DELETE");
            conf.addAllowedMethod("PUT");
            conf.addAllowedHeader(CorsConfiguration.ALL);
            return conf;
        })).csrf(ServerHttpSecurity.CsrfSpec::disable).authorizeExchange(requests -> requests
                .pathMatchers(
                        "/eureka/**",
                        "/actuator/**")
                .permitAll()
                .anyExchange().authenticated());
        http.oauth2ResourceServer().jwt();
        return http.build();
    }

}
