package com.recipemanager.controllers;

import com.recipemanager.datalayer.entity.Ingredient;
import com.recipemanager.datalayer.entity.Step;
import com.recipemanager.mappers.*;
import com.recipemanager.mappers.IngredientMapperImpl;
import com.recipemanager.mappers.RecipeMapperImpl;
import com.recipemanager.mappers.StepMapperImpl;
import com.recipemanager.service.*;
import org.example.recipeservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class RecipeServiceSoapEndpoint {
    private static final String namespaceUri = "http://www.example.org/RecipeService";

    private RecipeManagerService recipeManagerService;
    private RecipeMapper recipeMapper;
    private StepMapper stepMapper;
    private IngredientMapper ingredientMapper;

    @Autowired
    public void RecipeManagerService (RecipeManagerServiceImpl recipeManagerServiceImpl,
                                      StepMapperImpl stepMapperImpl,
                                      IngredientMapperImpl ingredientMapperImpl,
                                      RecipeMapperImpl recipeMapperImpl) {
        this.recipeManagerService = recipeManagerServiceImpl;
        this.ingredientMapper = ingredientMapperImpl;
        this.recipeMapper = recipeMapperImpl;
        this.stepMapper = stepMapperImpl;
    }

    @PayloadRoot(localPart = "GetRecipeRequest", namespace = namespaceUri)
    @ResponsePayload
    public GetRecipeResponse getRecipe(@RequestPayload GetRecipeRequest request) {
        Recipe recipe = recipeMapper.entityToModel(recipeManagerService.getRecipe(request.getId()));

        GetRecipeResponse response = new GetRecipeResponse();
        response.setRecipe(recipe);
        return response;
    }

    @PayloadRoot(localPart = "CreateRecipeRequest", namespace = namespaceUri)
    @ResponsePayload
    public CreateRecipeResponse createRecipe(@RequestPayload CreateRecipeRequest request) {
        String title = request.getRecipe().getTitle();
        Integer time = request.getRecipe().getTimeInMinutes();

        List<Step> steps = stepMapper.modelListToEntityList(request.getRecipe().getSteps());
        List<Ingredient> ingredients = ingredientMapper.modelListToEntityList(request.getRecipe().getIngredients());

        Long recipeId = recipeManagerService.createRecipe(title, time, steps, ingredients);

        CreateRecipeResponse response = new CreateRecipeResponse();
        response.setId(recipeId);

        return response;
    }
}
