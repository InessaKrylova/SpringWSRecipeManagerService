package com.recipemanager.service;

import com.recipemanager.datalayer.entity.*;
import com.recipemanager.datalayer.repository.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class RecipeManagerServiceImpl implements RecipeManagerService {

    private RecipeRepository recipeRepository;
    private StepRepository stepRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public RecipeManagerServiceImpl(RecipeRepository recipeRepository, StepRepository stepRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.stepRepository = stepRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Recipe getRecipe(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        Recipe recipe = recipeOptional.isPresent()? recipeOptional.get() : null ;

        recipe.setSteps(stepRepository.findAllByRecipeId(id));
        recipe.setIngredients(ingredientRepository.findAllByRecipeId(id));

        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        Iterable<Recipe> resultIterable = recipeRepository.findAll();
        /*Iterator<Recipe> iterator = resultIterable.iterator();

        while(iterator.hasNext()) {
            Recipe recipe = iterator.next();

            recipe.setSteps(stepRepository.findAllByRecipeId(recipe.getId()));
            recipe.setIngredients(ingredientRepository.findAllByRecipeId(recipe.getId()));
        }*/ //TODO

        List<Recipe> allRecipesEntities = new ArrayList<>();
        resultIterable.iterator().forEachRemaining(allRecipesEntities::add);
        return allRecipesEntities;
    }

    @Override
    public List<Recipe> findRecipeByTitle(String title) {
        List<Recipe> recipes = recipeRepository.findRecipeByTitle(title);
        /*Iterator<Recipe> iterator = recipes.iterator();

        while(iterator.hasNext()) {
            Recipe recipe = iterator.next();

            recipe.setSteps(stepRepository.findAllByRecipeId(recipe.getId()));
            recipe.setIngredients(ingredientRepository.findAllByRecipeId(recipe.getId()));
        }*/ //TODO
        return recipes;
    }

    @Override
    public List<Recipe> findRecipeByTime(Integer time) {
        List<Recipe> recipes = recipeRepository.findRecipeByTimeInMinutes(time);
        /*Iterator<Recipe> iterator = recipes.iterator();

        while(iterator.hasNext()) {
            Recipe recipe = iterator.next();

            recipe.setSteps(stepRepository.findAllByRecipeId(recipe.getId()));
            recipe.setIngredients(ingredientRepository.findAllByRecipeId(recipe.getId()));
        }*/ //TODO
        return recipes;
    }

    @Override
    public Long addStep(String title, Integer stepNumber, Long recipeId) {
        Step s = new Step(title, stepNumber, recipeRepository.findById(recipeId).get());
        return stepRepository.save(s).getId();
    }

    @Override
    public Long addIngredient(String title, Double amount, Long recipeId) {
        Ingredient i = new Ingredient(title, amount, recipeRepository.findById(recipeId).get());
        return ingredientRepository.save(i).getId();
    }

    @Override
    public Long createRecipe(String title, Integer timeInMinutes, List<Step> steps, List<Ingredient> ingredients) {
        Recipe recipe = new Recipe(title, timeInMinutes, steps, ingredients);
        Long newRecipeId = recipeRepository.save(recipe).getId();

        stepRepository.saveAll(steps);
        ingredientRepository.saveAll(ingredients);

        return newRecipeId;
    }


}