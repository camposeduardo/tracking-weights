package com.camposeduardo.trackingweights.api;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record ExerciseResponse (String name, LocalDate date, Integer reps, Double weight, String muscleGroup) {
}
