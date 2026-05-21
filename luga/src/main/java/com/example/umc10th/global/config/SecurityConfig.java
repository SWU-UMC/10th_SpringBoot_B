package com.example.umc10th.global.config;

import com.example.umc10th.global.security.JwtAccessDeniedHandler;
import com.example.umc10th.global.security.JwtAuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
            "/api/user/signup",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper, AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler) {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public JwtAccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new JwtAccessDeniedHandler(objectMapper);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAccessDeniedHandler accessDeniedHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll() // Public API
                        .anyRequest().authenticated() // Private API
                )

                // 인증 및 인가 실패 시 동일한 응답 제공
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint) // 4001
                        .accessDeniedHandler(accessDeniedHandler) // 4003
                );
        return http.build();
    }
}
