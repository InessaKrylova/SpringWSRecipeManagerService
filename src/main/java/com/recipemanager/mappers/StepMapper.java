package com.recipemanager.mappers;

import com.recipemanager.datalayer.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface StepMapper {

    @Mapping(source = "recipeId", target = "recipe")
    Step modelToEntity(org.example.recipeservice.Step step);

    @Mapping(source = "recipe", target = "recipeId")
    org.example.recipeservice.Step entityToModel(Step step);

    List<Step> modelListToEntityList(List<org.example.recipeservice.Step> stepModels);

    List<org.example.recipeservice.Step> entityListToModelList(List<Step> stepEntities);

    com.recipemanager.datalayer.entity.Recipe mapRecipeIdToRecipe(Long recipeId);

    default Long mapRecipeToRecipeId(com.recipemanager.datalayer.entity.Recipe recipe) {
        return recipe.getId();
    }
}
