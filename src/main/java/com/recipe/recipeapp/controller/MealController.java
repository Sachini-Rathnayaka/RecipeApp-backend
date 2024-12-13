package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.MealDto;
import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.dao.request.RecipeDto;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.model.MealType;
import com.hasthiyait.recipeapp.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    @PostMapping("/add-recipes/user/{userId}/recipe/{recipeId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<MealDto>> addRecipestoMeal(@Valid @RequestBody MealDto mealDto, @PathVariable("userId") Long userId, @PathVariable("recipeId") Long recipeId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Recipe added to meal Successfully!");
        ApiResponse<MealDto> response = new ApiResponse<>(status, this.mealService.addRecipesToMeal(mealDto, userId, recipeId));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // New endpoint to get recipes by meal type
    @GetMapping("/get-recipes/{mealType}/date/{date}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<MealDto>>> getRecipesByMealType(@PathVariable("mealType") MealType mealType,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<MealDto> recipes = this.mealService.getRecipesByMealType( mealType,date);
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Recipes retrieved successfully!");
        ApiResponse<List<MealDto>> response = new ApiResponse<>(status, recipes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-recipe/{mealId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<Void>> deleteRecipeFromMeal(@PathVariable("mealId") Long mealId) {
        this.mealService.deleteRecipeFromMeal(mealId);
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Recipe deleted from meal Successfully!");
        ApiResponse<Void> response = new ApiResponse<>(status, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
