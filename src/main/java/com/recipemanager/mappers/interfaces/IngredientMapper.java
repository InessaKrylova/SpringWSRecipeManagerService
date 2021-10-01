package com.recipemanager.mappers.interfaces;

import com.recipemanager.datalayer.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper //TODO
public interface IngredientMapper {

    @Mapping(source = "recipeId", target = "recipe") //TODO
    Ingredient modelToEntity(org.example.recipeservice.Ingredient ingredient);

    @Mapping(source = "recipeId", target = "recipe") //TODO
    org.example.recipeservice.Ingredient entityToModel(Ingredient ingredient);

    @Mapping(source = "recipeId", target = "recipe") //TODO
    List<Ingredient> modelListToEntityList(List<org.example.recipeservice.Ingredient> ingredients);

    @Mapping(source = "recipeId", target = "recipe") //TODO
    List<org.example.recipeservice.Ingredient> entityListToModelList(List<Ingredient> ingredients);
}

