package com.recipemanager.service;

import com.recipemanager.datalayer.entity.*;

import java.util.List;

public interface RecipeManagerService {
    Recipe getRecipe(Long id);

    Long createRecipe(String title, Integer timeInMinutes, List<Step> steps, List<Ingredient> ingredients);
    List<Recipe> getAllRecipes();

    List<Recipe> findRecipeByTitle(String title);
    List<Recipe> findRecipeByTime(Integer time);

    Long addStep(String title, Integer stepNumber, Long recipeId);
    Long addIngredient(String title, Double amount, Long recipeId);
}

