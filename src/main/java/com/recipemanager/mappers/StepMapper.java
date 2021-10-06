package com.recipemanager.mappers;

import com.recipemanager.datalayer.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface StepMapper {

    @Mapping(source = "recipeId", target = "recipe") //TODO
    Step modelToEntity(org.example.recipeservice.Step step);

    //@Mapping(source = "recipe", target = "recipeId") //TODO
    org.example.recipeservice.Step entityToModel(Step step);

    //@Mapping(source = "recipe", target = "recipeId") //TODO
    List<Step> modelListToEntityList(List<org.example.recipeservice.Step> stepModels);

    //@Mapping(source = "recipeId", target = "recipe") //TODO
    List<org.example.recipeservice.Step> entityListToModelList(List<Step> stepEntities);

    com.recipemanager.datalayer.entity.Recipe map(Long recipeId);
}
