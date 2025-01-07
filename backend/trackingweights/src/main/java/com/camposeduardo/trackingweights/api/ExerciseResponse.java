package com.camposeduardo.trackingweights.api;


import java.time.LocalDate;

public record ExerciseResponse (String exerciseName, LocalDate date, Integer reps, Double weight, String muscleGroup) {
}
