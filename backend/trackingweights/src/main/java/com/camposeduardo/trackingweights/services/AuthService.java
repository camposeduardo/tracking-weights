package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.LoginRequest;
import com.camposeduardo.trackingweights.api.RegisterRequest;
import com.camposeduardo.trackingweights.entities.User;
import com.camposeduardo.trackingweights.exceptions.UserAlreadyExistsException;
import com.camposeduardo.trackingweights.exceptions.UserNotFoundException;
import com.camposeduardo.trackingweights.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return jwtService.generateToken(user);
    }

    public void register(RegisterRequest request) {

        // check if the email exists
        if (checkIfEmailIsAlreadyInUse(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    public boolean checkIfEmailIsAlreadyInUse(String email){
        return this.userRepository.findByEmail(email).isPresent();
    }

    public ResponseCookie generateCookie(String token) {
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(600)
                .sameSite("Strict")
                .build();


        return cookie;
    }
}