package com.example.jwt.config;

import com.example.jwt.exception.JwtExampleExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable() // отключаем basic-auth
                .csrf().disable() // отключаем csrf
                .cors().disable() // отключаем cors
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // не создается и не хранится сессия
                .and()
                .authorizeHttpRequests(
                        authz -> authz
                                .antMatchers("/entry/login").permitAll() // на эту ручку могут сходить все
                                .anyRequest().authenticated() // остальные ручки закрыты
                                .and()
                                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                ).build();
    }

}
