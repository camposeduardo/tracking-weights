package com.camposeduardo.trackingweights.api;


import java.time.LocalDate;

public record ExerciseDto(Long id, String exerciseName, LocalDate date, Integer reps, Double weight, String muscleGroup) {
}
