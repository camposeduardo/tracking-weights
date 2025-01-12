package com.camposeduardo.trackingweights.mappers;

import com.camposeduardo.trackingweights.api.ExerciseDto;
import com.camposeduardo.trackingweights.entities.Exercise;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDto toResponse(Exercise entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExerciseFromDto(ExerciseDto exerciseDto, @MappingTarget Exercise exercise);

}
