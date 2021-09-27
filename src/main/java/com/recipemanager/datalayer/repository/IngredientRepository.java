package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAllByRecipeId(Long recipeId);
}
