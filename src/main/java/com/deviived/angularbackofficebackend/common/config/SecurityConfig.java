package com.deviived.angularbackofficebackend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/login", "/api/auth/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginProcessingUrl("/api/auth/login") // Custom login endpoint
                    .successHandler((request, response, authentication) -> response.setStatus(200)) // Handle successful login
                    .failureHandler((request, response, exception) -> response.setStatus(401)) // Handle login failure
            )
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/api/auth/oauth2/authorization/github") // GitHub OAuth2 login endpoint
                    .defaultSuccessUrl("/api/auth/success", true) // Redirect on success
                    .failureUrl("/api/auth/failure") // Redirect on failure
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
