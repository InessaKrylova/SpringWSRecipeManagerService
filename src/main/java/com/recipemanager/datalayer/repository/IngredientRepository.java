package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAllByRecipeId(Long recipeId);
}
