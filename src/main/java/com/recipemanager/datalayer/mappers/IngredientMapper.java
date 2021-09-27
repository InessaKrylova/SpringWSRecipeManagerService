package com.recipemanager.datalayer.mappers;

import com.recipemanager.datalayer.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "recipeId", target = "recipeId")
    Ingredient ingrModelToIngrEntity(org.example.recipeservice.Ingredient ingredient);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "recipeId", target = "recipeId")
    org.example.recipeservice.Ingredient ingrEntityToIngrModel(Ingredient ingredient);
}

