package com.camposeduardo.trackingweights.controllers;

import com.camposeduardo.trackingweights.api.LoginRequest;
import com.camposeduardo.trackingweights.api.RegisterRequest;
import com.camposeduardo.trackingweights.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        ResponseCookie cookie = authService.generateCookie(token);

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/auth/status")
    public ResponseEntity<Boolean> checkAuthentication() {
        return ResponseEntity.ok(authService.isAuthenticated());
    }

}
