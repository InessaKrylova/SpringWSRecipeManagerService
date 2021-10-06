package com.recipemanager.controllers;

import com.recipemanager.datalayer.entity.Ingredient;
import com.recipemanager.datalayer.entity.Step;
import com.recipemanager.mappers.RecipeMapper;
import com.recipemanager.mappers.RecipeMapperImpl;
import com.recipemanager.service.*;
import org.example.recipeservice.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeServiceRestController {

    private RecipeManagerService recipeManagerService;
    private RecipeMapper recipeMapper;

    @Autowired
    public RecipeServiceRestController(RecipeManagerServiceImpl recipeManagerServiceImpl,
                                       RecipeMapperImpl recipeMapperImpl) {
        this.recipeManagerService = recipeManagerServiceImpl;
        this.recipeMapper = recipeMapperImpl;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        List<Recipe> recipes = recipeMapper.entityListToModelList(recipeManagerService.getAllRecipes());
        StringBuilder allRecipes = new StringBuilder();
        for (Recipe recipe : recipes){
            allRecipes.append(recipeToString(recipe));
        }
        return new ResponseEntity<>(allRecipes.toString(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRecipe(@RequestParam(name="title") String title,
                                               @RequestParam(name="timeInMinutes") String timeInMinutes) {
        Long newRecipeId = recipeManagerService.createRecipe(
                title, Integer.valueOf(timeInMinutes),
                new ArrayList<Step>(), new ArrayList<Ingredient>());
        return new ResponseEntity<>("New recipe id="+newRecipeId, HttpStatus.OK);
    }

    @PostMapping("/addStep")
    public ResponseEntity<String> addStep(@RequestParam(name="recipeId") String recipeId,
                                           @RequestParam(name="stepNumber") String stepNumber,
                                           @RequestParam(name="stepText") String stepText) {
        Long newStepId = recipeManagerService.addStep(stepText, Integer.valueOf(stepNumber), Long.valueOf(recipeId));
        return new ResponseEntity<>("New step with id="+newStepId+" was added to recipe with id="+recipeId, HttpStatus.OK);
    }

    @PostMapping("/addIngredient")
    public ResponseEntity<String> addIngredient(@RequestParam(name="recipeId") String recipeId,
                                              @RequestParam(name="title") String title,
                                              @RequestParam(name="amount") String amount) {
        Long newIngrId = recipeManagerService.addIngredient(title, Double.valueOf(amount), Long.valueOf(recipeId));
        return new ResponseEntity<>("New ingredient with id="+newIngrId+" was added to recipe with id="+recipeId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getRecipe(@PathVariable("id") Long id) {
        Recipe recipe = recipeMapper.entityToModel(recipeManagerService.getRecipe(id));
        return new ResponseEntity<>(recipeToString(recipe), HttpStatus.OK);
    }

    @GetMapping("/findRecipeByTime")
    public ResponseEntity<String> findRecipeByTime(@RequestParam(name="time") String time) {
        List<Recipe> recipes = recipeMapper.entityListToModelList(recipeManagerService.findRecipeByTime(Integer.valueOf(time)));
        return new ResponseEntity<>(listToString(recipes), HttpStatus.OK);
    }

    @GetMapping("/findRecipeByTitle")
    public ResponseEntity<String> findRecipeByTitle(@RequestParam(name="title") String title) {
        List<Recipe> recipes = recipeMapper.entityListToModelList(recipeManagerService.findRecipeByTitle(title));
        return new ResponseEntity<>(listToString(recipes), HttpStatus.OK);
    }

    private String recipeToString(Recipe recipe) {
        StringBuilder line = new StringBuilder();
        return line.append("[")
                .append("id="+recipe.getId()+", ")
                .append("title='"+recipe.getTitle()+"', ")
                .append("time="+recipe.getTimeInMinutes()+", ")
                .append("ingredientsCount="+recipe.getIngredients().size())
                .append("],\n").toString();
    }

    private String listToString(List<Recipe> recipes) {
        StringBuilder allRecipes = new StringBuilder();
        for (Recipe r : recipes){
            allRecipes.append(recipeToString(r));
        }
        if (allRecipes.toString().isEmpty()) allRecipes = new StringBuilder("There are no recipes matching your request");
        return allRecipes.toString();
    }
}
