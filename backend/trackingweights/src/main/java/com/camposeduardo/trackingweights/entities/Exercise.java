package com.camposeduardo.trackingweights.entities;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String exerciseName;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private Integer reps;

    @NotNull
    @Column(nullable = false)
    private Double weight;

    @NotNull
    @Column(nullable = false)
    private String unit;

    @NotNull
    @Column(nullable = false)
    private String muscleGroup;

    /*
    @Column(nullable = false)
    private User user;

     */
}
