package com.deviived.angularbackofficebackend.common.auth.controller;

import com.deviived.angularbackofficebackend.common.auth.dto.LoginDTO;
import com.deviived.angularbackofficebackend.common.auth.model.UserEntity;
import com.deviived.angularbackofficebackend.common.auth.service.AuthService;
import com.deviived.angularbackofficebackend.common.auth.utils.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final TokenBlacklistService tokenBlacklistService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    try {
      String token = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
      return ResponseEntity.ok(Map.of("token", token));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body("Invalid token");
    }

    String token = authHeader.substring(7);
    tokenBlacklistService.addToBlacklist(token); // Blacklist the token
    return ResponseEntity.ok("Logged out successfully");
  }

  @PostMapping("/register")
  public ResponseEntity<UserEntity> register(@RequestBody LoginDTO loginDTO) {
    UserEntity user = authService.register(loginDTO.getEmail(), loginDTO.getPassword());
    return ResponseEntity.ok(user);
  }
}
