package com.crm.portal.controller;

import com.crm.portal.dto.AuthResponse;
import com.crm.portal.dto.LoginRequest;
import com.crm.portal.dto.RegisterRequest;
import com.crm.portal.dto.UserDto;
import com.crm.portal.security.CustomUserDetails;
import com.crm.portal.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@AuthenticationPrincipal CustomUserDetails principal) {
        return ResponseEntity.ok(authService.me(principal.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Stateless JWT: there is no server-side session to invalidate.
        // The frontend clears its stored token on logout (see AuthContext.jsx).
        return ResponseEntity.noContent().build();
    }
}
