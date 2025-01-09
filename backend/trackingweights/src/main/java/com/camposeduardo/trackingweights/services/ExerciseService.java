package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.ExerciseResponse;
import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.entities.User;
import com.camposeduardo.trackingweights.exceptions.InvalidExerciseException;
import com.camposeduardo.trackingweights.exceptions.UserNotFoundException;
import com.camposeduardo.trackingweights.mappers.ExerciseMapper;
import com.camposeduardo.trackingweights.repositories.ExerciseRepository;
import com.camposeduardo.trackingweights.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final AuthService authService;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseResponse addExercise(Exercise exercise) {

        if (exercise == null) {
            throw new InvalidExerciseException();
        }

        User user = authService.getUser();

        exercise.setUser(user);
        exerciseRepository.save(exercise);
        return exerciseMapper.toResponse(exercise);
    }

    public List<ExerciseResponse> findExercises(String searchRequest) {

        if (searchRequest == null) {
            return null;
        }

        User user = authService.getUser();

        Optional<List<Exercise>> exercises =
                exerciseRepository.findByExerciseNameIgnoreCaseContainingAndUserId(searchRequest, user.getId());

        List<ExerciseResponse> exercisesResponse = new ArrayList<>();

        if (exercises.isEmpty()) {
            return null;
        }

        for (Exercise exercise : exercises.get()) {
            exercisesResponse.add(exerciseMapper.toResponse(exercise));
        }

        return exercisesResponse;
    }

    public List<String> getAllMuscleGroups() {
        return exerciseRepository.getAllMuscleGroups();
    }

    public List<ExerciseResponse> getExercisesByMuscleGroup(String muscleGroup) {

        if (muscleGroup.isEmpty()) {
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Optional<List<Exercise>> exercises = exerciseRepository.findByMuscleGroup(
                StringUtils.capitalize(muscleGroup), user.get().getId());

        if (exercises.isEmpty()) {
            return null;
        }

        List<ExerciseResponse> exercisesResponse = new ArrayList<>();

        for (Exercise exercise : exercises.get()) {
            exercisesResponse.add(exerciseMapper.toResponse(exercise));
        }

        return exercisesResponse;
    }

    public void deleteExercise(Long exerciseId) {

        if (exerciseId == null) {
            throw new InvalidExerciseException();
        }

        Optional<Exercise> tempExercise = exerciseRepository.findById(exerciseId);

        if (tempExercise.isEmpty()) {
            throw new InvalidExerciseException();
        }

        exerciseRepository.delete(tempExercise.get());
    }

}
