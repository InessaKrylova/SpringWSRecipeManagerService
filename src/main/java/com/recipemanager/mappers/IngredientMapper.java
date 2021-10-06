package com.recipemanager.mappers;

import com.recipemanager.datalayer.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IngredientMapper {

    @Mapping(source = "recipeId", target = "recipe")
    Ingredient modelToEntity(org.example.recipeservice.Ingredient ingredient);

    @Mapping(source = "recipe", target = "recipeId")
    org.example.recipeservice.Ingredient entityToModel(Ingredient ingredient);

    List<Ingredient> modelListToEntityList(List<org.example.recipeservice.Ingredient> ingredientModels);

    List<org.example.recipeservice.Ingredient> entityListToModelList(List<Ingredient> ingredientEntities);

    com.recipemanager.datalayer.entity.Recipe mapRecipeIdToRecipe(Long recipeId);

    default Long mapRecipeToRecipeId(com.recipemanager.datalayer.entity.Recipe recipe) {
        return recipe.getId();
    }
}

