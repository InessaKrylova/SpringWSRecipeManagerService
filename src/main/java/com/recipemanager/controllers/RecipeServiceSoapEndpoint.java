package com.recipemanager.controllers;

import com.recipemanager.datalayer.mappers.ModelToEntityMapper;
import com.recipemanager.datalayer.entity.Ingredient;
import com.recipemanager.datalayer.entity.Step;
import com.recipemanager.service.RecipeManagerService;
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
    private ModelToEntityMapper mapper;

    @Autowired
    public void RecipeManagerService (RecipeManagerService recipeManagerService, ModelToEntityMapper mapper) {
        this.recipeManagerService = recipeManagerService;
        this.mapper = mapper;
    }

    @PayloadRoot(localPart = "GetRecipeRequest", namespace = namespaceUri)
    @ResponsePayload
    public GetRecipeResponse getRecipe(@RequestPayload GetRecipeRequest request) throws Exception {
        Recipe recipe = mapper.mapRecipeEntityToModel(recipeManagerService.getRecipe(request.getId()));

        GetRecipeResponse response = new GetRecipeResponse();
        response.setRecipe(recipe);
        return response;
    }

    @PayloadRoot(localPart = "CreateRecipeRequest", namespace = namespaceUri)
    @ResponsePayload
    public CreateRecipeResponse createRecipe(@RequestPayload CreateRecipeRequest request)
            throws Exception {
        String title = request.getRecipe().getTitle();
        Integer time = request.getRecipe().getTimeInMinutes();

        List<Step> steps = mapper.mapListStepsModelsToEntities(request.getRecipe().getSteps());
        List<Ingredient> ingredients = mapper.mapListIngredientModelsToEntities(request.getRecipe().getIngredients());

        Long recipeId = recipeManagerService.createRecipe(title, time, steps, ingredients);

        CreateRecipeResponse response = new CreateRecipeResponse();
        response.setId(recipeId);

        return response;
    }
}
