package com.camposeduardo.trackingweights.controllers;

import com.camposeduardo.trackingweights.api.ExerciseResponse;
import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/add")
    public ResponseEntity<ExerciseResponse> addExercise(@Valid @RequestBody Exercise exercise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.addExercise(exercise));
    }
}
