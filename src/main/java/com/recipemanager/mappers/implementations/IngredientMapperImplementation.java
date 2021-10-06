package com.recipemanager.mappers.implementations;

import com.recipemanager.datalayer.entity.Ingredient;
import com.recipemanager.datalayer.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientMapperImplementation {

    public Ingredient modelToEntity(org.example.recipeservice.Ingredient ingredientModel) {
        Recipe recipe = new Recipe();
        recipe.setId(ingredientModel.getRecipeId());

        return new Ingredient(
                ingredientModel.getTitle(),
                ingredientModel.getAmount(),
                recipe
        );
    }

    public org.example.recipeservice.Ingredient entityToModel(Ingredient ingredientEntity) {
        org.example.recipeservice.Ingredient ingredientModel = new org.example.recipeservice.Ingredient();
        ingredientModel.setTitle(ingredientEntity.getTitle());
        ingredientModel.setAmount(ingredientEntity.getAmount());
        ingredientModel.setRecipeId(ingredientEntity.getRecipe().getId());
        return ingredientModel;
    }

    public Recipe map(Long recipeId) {
        return null;
    }

    public List<Ingredient> modelListToEntityList(List<org.example.recipeservice.Ingredient> modelsList) {
        return modelsList.stream().map(i -> modelToEntity(i)).collect(Collectors.toList());
    }

    public List<org.example.recipeservice.Ingredient> entityListToModelList(List<Ingredient> entitiesList) {
        return entitiesList.stream().map(i -> entityToModel(i)).collect(Collectors.toList());
    }


}
