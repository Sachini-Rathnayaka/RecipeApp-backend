package com.hasthiyait.recipeapp.repository;


import com.hasthiyait.recipeapp.model.Recipe;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NonNullApi
@Repository
@SuppressWarnings("null")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findById(Long recipeId);
}