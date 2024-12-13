package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.MealDto;
import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.dao.request.RecipeDto;
import com.hasthiyait.recipeapp.model.Meal;
import com.hasthiyait.recipeapp.model.MealType;

import java.util.Date;
import java.util.List;

public interface MealService {

    //create
    MealDto addRecipesToMeal(MealDto mealDto, Long userId, Long recipeId) ;
    List<MealDto> getRecipesByMealType(MealType mealType, Date date);
    void deleteRecipeFromMeal(Long mealId);




}
