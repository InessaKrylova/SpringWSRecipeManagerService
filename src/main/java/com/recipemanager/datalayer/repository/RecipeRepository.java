package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findRecipeByTimeInMinutes(Integer timeInMinutes);
    List<Recipe> findRecipeByTitle(String title);
}