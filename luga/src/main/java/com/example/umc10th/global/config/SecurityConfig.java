package com.example.umc10th.global.config;

import com.example.umc10th.global.security.CustomUserDetailService;
import com.example.umc10th.global.security.JwtAccessDeniedHandler;
import com.example.umc10th.global.security.JwtAuthenticationEntryPoint;
import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    private static final String[] PUBLIC_URLS = {
            "/api/user/signup",
            "/api/user/login",
            "/api/user/*/food-preferences",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFiler() {
        return new JwtAuthFilter(jwtUtil, customUserDetailService);
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
                )
                .addFilterBefore(jwtAuthFiler(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
