package com.recipemanager.datalayer.repository;

import com.recipemanager.datalayer.entity.Step;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends CrudRepository<Step, Long> {
    List<Step> findAllByRecipeId(Long recipeId);
}
