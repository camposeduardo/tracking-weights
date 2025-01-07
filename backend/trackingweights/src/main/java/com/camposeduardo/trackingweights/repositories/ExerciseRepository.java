package com.camposeduardo.trackingweights.repositories;

import com.camposeduardo.trackingweights.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query("SELECT e FROM Exercise e JOIN e.user u WHERE e.muscleGroup =:muscleGroup AND u.id =:user_id")
    Optional<List<Exercise>> findByMuscleGroup(@Param("muscleGroup") String muscleGroup,
                                               @Param("user_id") Integer userId);

    @Query(value = "SELECT name FROM muscle_group", nativeQuery = true)
    List<String> getAllMuscleGroups();

    Optional<List<Exercise>> findByExerciseNameIgnoreCaseContainingAndUserId(String exerciseName, int userId);
}
