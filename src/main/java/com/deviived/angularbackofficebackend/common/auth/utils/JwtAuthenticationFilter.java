package com.deviived.angularbackofficebackend.common.auth.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (isAuthPath(request) || authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7); // Extract token without "Bearer "

        // 🔹 Reject if token is blacklisted
        if (tokenBlacklistService.isBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is invalid");
            return;
        }

        decodeJwtParts(token);
        authenticateUser(token);

        filterChain.doFilter(request, response);
    }

    /**
     * Checks if the request is for authentication paths.
     */
    private boolean isAuthPath(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/auth/login") || path.equals("/auth/register");
    }

    /**
     * Decodes and logs JWT parts for debugging.
     */
    private void decodeJwtParts(String jwt) {
        try {
            String[] tokenParts = jwt.split("\\.");
            if (tokenParts.length < 3) {
                log.warn("Invalid JWT format");
                return;
            }

            String header = new String(Base64.getUrlDecoder().decode(tokenParts[0]), StandardCharsets.UTF_8);
            String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]), StandardCharsets.UTF_8);

            log.info("Decoded Header: {}", header);
            log.info("Decoded Payload: {}", payload);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            log.error("Error decoding JWT: {}", e.getMessage());
        }
    }

    /**
     * Extracts username from JWT and sets authentication.
     */
    private void authenticateUser(String jwt) {
        String username = jwtUtil.extractUsername(jwt);
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
