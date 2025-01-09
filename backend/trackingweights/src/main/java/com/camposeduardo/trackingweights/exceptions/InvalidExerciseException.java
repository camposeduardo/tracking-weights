package com.camposeduardo.trackingweights.exceptions;

public class InvalidExerciseException extends RuntimeException {

    public InvalidExerciseException() {
        super("Exercise is invalid.");
    }
}
