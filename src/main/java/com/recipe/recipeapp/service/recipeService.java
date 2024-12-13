package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.recipeRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.exception.categoryNotFountExeption;
import com.hasthiyait.recipeapp.exception.dishNotFoundExeption;
import com.hasthiyait.recipeapp.model.Recipe;
import org.springframework.data.domain.Page;

import java.util.List;

public interface recipeService {
    Recipe addNewRecipe(recipeRequest recipeRequest) throws dishNotFoundExeption, categoryNotFountExeption;

//    List<Recipe> getAllRecipe() throws NotFountException;

    Page<Recipe> getAllRecipe(int offset, int pageSize) throws NotFountException;

    Recipe getRecipeById(Integer id) throws NotFountException;
}
