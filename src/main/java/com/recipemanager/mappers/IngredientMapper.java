package com.recipemanager.mappers;

import com.recipemanager.datalayer.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IngredientMapper {

    @Mapping(source = "recipeId", target = "recipe") //TODO
    Ingredient modelToEntity(org.example.recipeservice.Ingredient ingredient);

    @Mapping(source = "recipe", target = "recipeId") //TODO
    org.example.recipeservice.Ingredient entityToModel(Ingredient ingredient);

    //@Mapping(source = "recipe", target = "recipeId") //TODO
    List<Ingredient> modelListToEntityList(List<org.example.recipeservice.Ingredient> ingredientModels);

    //@Mapping(source = "recipeId", target = "recipe") //TODO
    List<org.example.recipeservice.Ingredient> entityListToModelList(List<Ingredient> ingredientEntities);

    com.recipemanager.datalayer.entity.Recipe map(Long recipeId);
}

