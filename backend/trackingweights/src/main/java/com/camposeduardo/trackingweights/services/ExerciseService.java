package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.ExerciseResponse;
import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.entities.User;
import com.camposeduardo.trackingweights.mappers.ExerciseMapper;
import com.camposeduardo.trackingweights.repositories.ExerciseRepository;
import com.camposeduardo.trackingweights.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseResponse addExercise(Exercise exercise) {

        if (exercise == null) {
            return null; // change to an exception later
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return null; // change to an exception later
        }

        exercise.setUser(user.get());
        exerciseRepository.save(exercise);
        return exerciseMapper.toResponse(exercise);
    }

    public List<String> getAllMuscleGroups() {
        return exerciseRepository.getAllMuscleGroups();
    }

    public List<Exercise> getExercisesByMuscleGroup(String muscleGroup) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return null; // change to an exception later
        }

        Optional<List<Exercise>> exercises = exerciseRepository.findByMuscleGroup(
                 muscleGroup, user.get().getId());

        if (exercises.isEmpty()) {
            return null; // change to an exception later
        }

        return exercises.get();
    }

}
