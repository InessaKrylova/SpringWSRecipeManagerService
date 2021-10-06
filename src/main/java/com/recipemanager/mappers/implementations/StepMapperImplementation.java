package com.recipemanager.mappers.implementations;

import com.recipemanager.datalayer.entity.Recipe;
import com.recipemanager.datalayer.entity.Step;

import java.util.List;
import java.util.stream.Collectors;

public class StepMapperImplementation {
    public Step modelToEntity(org.example.recipeservice.Step stepModel) {
        Recipe recipe = new Recipe();
        recipe.setId(stepModel.getRecipeId());

        return new com.recipemanager.datalayer.entity.Step(
                stepModel.getTitle(),
                stepModel.getStepNumber(),
                recipe //TODO
        );
    }

    public org.example.recipeservice.Step entityToModel(Step stepEntity) {
        org.example.recipeservice.Step stepModel = new org.example.recipeservice.Step();
        stepModel.setTitle(stepEntity.getTitle());
        stepModel.setStepNumber(stepEntity.getStepNumber());
        stepModel.setRecipeId(stepEntity.getRecipe().getId());
        return stepModel;
    }

    public Recipe map(Long recipeId) {
        return null;
    }

    public List<Step> modelListToEntityList(List<org.example.recipeservice.Step> stepModels) {
        return stepModels.stream().map(s -> modelToEntity(s)).collect(Collectors.toList());
    }

    public List<org.example.recipeservice.Step> entityListToModelList(List<Step> stepEntities) {
        return stepEntities.stream().map(s -> entityToModel(s)).collect(Collectors.toList());
    }
}
