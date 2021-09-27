package com.recipemanager.datalayer.mappers;

import com.recipemanager.datalayer.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StepMapper {

    StepMapper INSTANCE = Mappers.getMapper(StepMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "stepNumber", target = "stepNumber")
    @Mapping(source = "recipeId", target = "recipeId")
    Step stepModelToStepEntity(org.example.recipeservice.Step step);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "stepNumber", target = "stepNumber")
    @Mapping(source = "recipeId", target = "recipeId")
    org.example.recipeservice.Step stepEntityToStepModel(Step step);
}
