package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.ExerciseResponse;
import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.mappers.ExerciseMapper;
import com.camposeduardo.trackingweights.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseResponse addExercise(Exercise exercise) {

        if (exercise == null) {
            return null; // change to an exception later
        }

        exerciseRepository.save(exercise);
        return exerciseMapper.toResponse(exercise);
    }

}
