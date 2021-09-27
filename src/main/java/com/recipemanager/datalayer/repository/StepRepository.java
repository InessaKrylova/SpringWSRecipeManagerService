package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Step;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StepRepository extends CrudRepository<Step, Long> {
    List<Step> findAllByRecipeId(Long recipeId);
}
