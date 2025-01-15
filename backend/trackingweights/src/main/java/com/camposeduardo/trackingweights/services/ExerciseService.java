package com.camposeduardo.trackingweights.services;

import com.camposeduardo.trackingweights.api.ExerciseDto;
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
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final AuthService authService;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseDto addExercise(Exercise exercise) {

        if (exercise == null) {
            throw new InvalidExerciseException();
        }

        User user = authService.getUser();

        exercise.setUser(user);
        exerciseRepository.save(exercise);
        return exerciseMapper.toResponse(exercise);
    }

    public List<ExerciseDto> getAllExercises() {

        User user = authService.getUser();

        Optional<List<Exercise>> userExercises = exerciseRepository.getAllByUserId(user.getId());

        if (userExercises.isEmpty()) {
            return null;
        }

        List<ExerciseDto> exerciseDtos = new ArrayList<>();

        for (Exercise exercise : userExercises.get()) {
            exerciseDtos.add(exerciseMapper.toResponse(exercise));
        }

        return exerciseDtos;
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(InvalidExerciseException::new);
    }

    public List<ExerciseDto> findExercises(String searchRequest) {

        if (searchRequest == null) {
            return null;
        }

        User user = authService.getUser();

        Optional<List<Exercise>> exercises =
                exerciseRepository.findByExerciseNameIgnoreCaseContainingAndUserId(searchRequest, user.getId());

        List<ExerciseDto> exercisesResponse = new ArrayList<>();

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

    public List<ExerciseDto> getExercisesByMuscleGroup(String muscleGroup) {

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

        List<ExerciseDto> exercisesResponse = new ArrayList<>();

        for (Exercise exercise : exercises.get()) {
            exercisesResponse.add(exerciseMapper.toResponse(exercise));
        }

        return exercisesResponse;
    }

    public ExerciseDto updateExercise(ExerciseDto exerciseDto) {
        Exercise tempExercise = this.getExerciseById(exerciseDto.id());

        User user = authService.getUser();

        if (!Objects.equals(user.getId(), tempExercise.getUser().getId())) {
            // change to a specific exception later
            throw new RuntimeException("Exercise does not belong to this user");
        }

        exerciseMapper.updateExerciseFromDto(exerciseDto, tempExercise);
        exerciseRepository.save(tempExercise);
        return exerciseDto;

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
