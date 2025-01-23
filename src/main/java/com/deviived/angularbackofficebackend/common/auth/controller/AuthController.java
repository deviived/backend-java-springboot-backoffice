package com.deviived.angularbackofficebackend.common.auth.controller;

import com.deviived.angularbackofficebackend.common.auth.dto.LoginDTO;
import com.deviived.angularbackofficebackend.common.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = authService.login(loginDTO);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/success")
    public String oauth2Success(Authentication authentication) {
        return "OAuth2 Login successful: " + authentication.getName();
    }

    @GetMapping("/failure")
    public String oauth2Failure() {
        return "OAuth2 Login failed";
    }
}