package com.camposeduardo.trackingweights.api;


import java.time.LocalDate;

public record ExerciseResponse (Long id, String exerciseName, LocalDate date, Integer reps, Double weight, String muscleGroup) {
}
