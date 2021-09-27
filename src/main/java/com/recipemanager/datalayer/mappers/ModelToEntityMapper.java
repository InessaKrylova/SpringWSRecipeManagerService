package com.recipemanager.datalayer.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ModelToEntityMapper {

    public com.recipemanager.datalayer.entity.Step mapStepModelToEntity(org.example.recipeservice.Step stepModel) {
        return new com.recipemanager.datalayer.entity.Step(
                stepModel.getTitle(),
                stepModel.getStepNumber(),
                stepModel.getRecipeId()
        );
    }

    public com.recipemanager.datalayer.entity.Ingredient mapIngredientModelToEntity(org.example.recipeservice.Ingredient ingrModel) {
        return new com.recipemanager.datalayer.entity.Ingredient(
                ingrModel.getTitle(),
                ingrModel.getAmount(),
                ingrModel.getRecipeId()
        );
    }

    public com.recipemanager.datalayer.entity.Recipe mapRecipeModelToEntity(org.example.recipeservice.Recipe recipeModel) {
        return new com.recipemanager.datalayer.entity.Recipe(
                recipeModel.getId(),
                recipeModel.getTitle(),
                recipeModel.getTimeInMinutes(),
                mapListStepsModelsToEntities(recipeModel.getSteps()),
                mapListIngredientModelsToEntities(recipeModel.getIngredients())
        );
    }

    public org.example.recipeservice.Step mapStepEntityToModel(com.recipemanager.datalayer.entity.Step stepEntity) {
        org.example.recipeservice.Step stepModel = new org.example.recipeservice.Step();
        stepModel.setTitle(stepEntity.getTitle());
        stepModel.setStepNumber(stepEntity.getStepNumber());
        stepModel.setRecipeId(stepEntity.getRecipe().getId());
        return stepModel;
    }

    public org.example.recipeservice.Ingredient mapIngredientEntityToModel(com.recipemanager.datalayer.entity.Ingredient ingrEntity) {
        org.example.recipeservice.Ingredient ingrModel = new org.example.recipeservice.Ingredient();
        ingrModel.setTitle(ingrEntity.getTitle());
        ingrModel.setAmount(ingrEntity.getAmount());
        ingrModel.setRecipeId(ingrEntity.getRecipe().getId());
        return ingrModel;
    }

    public org.example.recipeservice.Recipe mapRecipeEntityToModel(com.recipemanager.datalayer.entity.Recipe recipeEntity) {
        org.example.recipeservice.Recipe recipeModel = new org.example.recipeservice.Recipe();
        recipeModel.setTitle(recipeEntity.getTitle());
        recipeModel.setId(recipeEntity.getId());
        recipeModel.setTimeInMinutes(recipeEntity.getTimeInMinutes());

        List<org.example.recipeservice.Ingredient> ingredientsList = mapListIngredientEntitiesToModels(recipeEntity.getIngredients());
        ingredientsList.forEach(i -> recipeModel.getIngredients().add(i)); //specifics of xjc generated classes - they don`t have setters for lists

        List<org.example.recipeservice.Step> stepsList = mapListStepsEntitiesToModels(recipeEntity.getSteps());
        stepsList.forEach(s -> recipeModel.getSteps().add(s)); //specifics of xjc generated classes - they don`t have setters for lists

        return recipeModel;
    }

    public  List<org.example.recipeservice.Recipe> mapListRecipesEntitiesToModels(List<com.recipemanager.datalayer.entity.Recipe> recipesEntitiesList) {
        return recipesEntitiesList.stream().map(r -> mapRecipeEntityToModel(r)).collect(Collectors.toList());
    }

    public List<org.example.recipeservice.Step> mapListStepsEntitiesToModels(List<com.recipemanager.datalayer.entity.Step> stepsEntitiesList) {
        return stepsEntitiesList.stream().map(r -> mapStepEntityToModel(r)).collect(Collectors.toList());
    }

    public List<org.example.recipeservice.Ingredient> mapListIngredientEntitiesToModels(List<com.recipemanager.datalayer.entity.Ingredient> ingredientsEntitiesList) {
        return ingredientsEntitiesList.stream().map(r -> mapIngredientEntityToModel(r)).collect(Collectors.toList());
    }

    public  List<com.recipemanager.datalayer.entity.Recipe> mapListRecipesModelsToEntities(List<org.example.recipeservice.Recipe> recipesModelsList) {
        return recipesModelsList.stream().map(r -> mapRecipeModelToEntity(r)).collect(Collectors.toList());
    }

    public List<com.recipemanager.datalayer.entity.Step> mapListStepsModelsToEntities(List<org.example.recipeservice.Step> stepsModelsList) {
        return stepsModelsList.stream().map(r -> mapStepModelToEntity(r)).collect(Collectors.toList());
    }

    public List<com.recipemanager.datalayer.entity.Ingredient> mapListIngredientModelsToEntities(List<org.example.recipeservice.Ingredient> ingredientsModelsList) {
        return ingredientsModelsList.stream().map(r -> mapIngredientModelToEntity(r)).collect(Collectors.toList());
    }
}