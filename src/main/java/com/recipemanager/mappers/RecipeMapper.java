package com.recipemanager.mappers;

import com.recipemanager.datalayer.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe modelToEntity(org.example.recipeservice.Recipe recipeModel);

    org.example.recipeservice.Recipe entityToModel(Recipe recipeEntity);

    List<Recipe> modelListToEntityList(List<org.example.recipeservice.Recipe> recipeModels);

    List<org.example.recipeservice.Recipe> entityListToModelList(List<Recipe> recipeEntities);
}
