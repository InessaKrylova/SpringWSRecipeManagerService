package com.recipemanager.datalayer.mappers;

import com.recipemanager.datalayer.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "recipeId", target = "recipeId")
    Recipe recipeModelToRecipeEntity(org.example.recipeservice.Recipe recipe);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "recipeId", target = "recipeId")
    org.example.recipeservice.Recipe recipeEntityToRecipeModel(Recipe recipe);
}
