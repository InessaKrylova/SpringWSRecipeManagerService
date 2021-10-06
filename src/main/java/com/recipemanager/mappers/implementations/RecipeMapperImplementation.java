package com.recipemanager.mappers.implementations;

import com.recipemanager.datalayer.entity.Recipe;
import com.recipemanager.mappers.IngredientMapper;
import com.recipemanager.mappers.StepMapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeMapperImplementation  {

    private final IngredientMapper ingredientMapper = Mappers.getMapper(IngredientMapper.class);
    private final StepMapper stepMapper = Mappers.getMapper(StepMapper.class);

    public Recipe modelToEntity(org.example.recipeservice.Recipe recipeModel) {
        return new com.recipemanager.datalayer.entity.Recipe(
                recipeModel.getId(),
                recipeModel.getTitle(),
                recipeModel.getTimeInMinutes(),
                stepMapper.modelListToEntityList(recipeModel.getSteps()),
                ingredientMapper.modelListToEntityList(recipeModel.getIngredients())
        );
    }

    public org.example.recipeservice.Recipe entityToModel(Recipe recipeEntity) {
        org.example.recipeservice.Recipe recipeModel = new org.example.recipeservice.Recipe();
        recipeModel.setTitle(recipeEntity.getTitle());
        recipeModel.setId(recipeEntity.getId());
        recipeModel.setTimeInMinutes(recipeEntity.getTimeInMinutes());

        List<org.example.recipeservice.Ingredient> ingredientsList = ingredientMapper.entityListToModelList(recipeEntity.getIngredients());
        ingredientsList.forEach(i -> recipeModel.getIngredients().add(i)); //specifics of xjc generated classes - they don`t have setters for lists

        List<org.example.recipeservice.Step> stepsList = stepMapper.entityListToModelList(recipeEntity.getSteps());
        stepsList.forEach(s -> recipeModel.getSteps().add(s)); //specifics of xjc generated classes - they don`t have setters for lists

        return recipeModel;
    }

    public List<Recipe> modelListToEntityList(List<org.example.recipeservice.Recipe> recipeModels) {
        return recipeModels.stream().map(r -> modelToEntity(r)).collect(Collectors.toList());
    }

    public List<org.example.recipeservice.Recipe> entityListToModelList(List<Recipe> recipeEntities) {
        return recipeEntities.stream().map(r -> entityToModel(r)).collect(Collectors.toList());
    }
}
