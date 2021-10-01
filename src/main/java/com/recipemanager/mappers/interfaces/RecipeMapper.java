package com.recipemanager.mappers.interfaces;

import com.recipemanager.datalayer.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RecipeMapper {

    //TODO Mapping
    Recipe modelToEntity(org.example.recipeservice.Recipe recipeModel);

    //TODO Mapping
    org.example.recipeservice.Recipe entityToModel(Recipe recipeEntity);

    //TODO Mapping
    List<Recipe> modelListToEntityList(List<org.example.recipeservice.Recipe> recipeModels);

    //TODO Mapping
    List<org.example.recipeservice.Recipe> entityListToModelList(List<Recipe> recipeEntities);
}
