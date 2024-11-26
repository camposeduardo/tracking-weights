package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.LoginRequest;
import com.camposeduardo.trackingweights.api.RegisterRequest;
import com.camposeduardo.trackingweights.entities.Exercise;
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

import java.util.ArrayList;
import java.util.List;

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

        if (checkIfEmailIsAlreadyInUse(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        List<Exercise> exerciseList = new ArrayList<>();
        user.setExercises(exerciseList);

        userRepository.save(user);
    }

    public boolean checkIfEmailIsAlreadyInUse(String email){
        return this.userRepository.findByEmail(email).isPresent();
    }

    public ResponseCookie generateCookie(String token) {
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(false) // change to true later
                .secure(false) // change to true later
                .path("/")
                .maxAge(900)
                .sameSite("Strict")
                .build();

        return cookie;
    }
}
