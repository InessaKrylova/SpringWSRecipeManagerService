package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Component
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findRecipeByTimeInMinutes(Integer timeInMinutes);
    List<Recipe> findRecipeByTitle(String title);
}