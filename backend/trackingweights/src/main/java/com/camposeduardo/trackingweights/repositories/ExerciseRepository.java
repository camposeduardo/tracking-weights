package com.camposeduardo.trackingweights.repositories;

import com.camposeduardo.trackingweights.entities.Exercise;
import com.camposeduardo.trackingweights.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
