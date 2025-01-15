package com.camposeduardo.trackingweights.controllers;

import com.camposeduardo.trackingweights.api.ExerciseDto;
import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/add")
    public ResponseEntity<ExerciseDto> addExercise(@Valid @RequestBody Exercise exercise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.addExercise(exercise));
    }

    @GetMapping("/{muscleGroup}")
    public ResponseEntity<List<ExerciseDto>> getExercisesByMuscleGroup(@PathVariable String muscleGroup) {
        return ResponseEntity.ok(exerciseService.getExercisesByMuscleGroup(muscleGroup));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/muscleGroups")
    public ResponseEntity<List<String>> getAllMuscleGroups() {
        return ResponseEntity.ok(exerciseService.getAllMuscleGroups());
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getExercises(@RequestParam String search) {
        return ResponseEntity.ok(exerciseService.findExercises(search));
    }

    @PostMapping("/update")
    public ResponseEntity<ExerciseDto> updateExercise(@Valid @RequestBody ExerciseDto exercise) {
        exerciseService.updateExercise(exercise);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{exerciseId}")
    public ResponseEntity<Void> getExercisesByMuscleGroup(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
