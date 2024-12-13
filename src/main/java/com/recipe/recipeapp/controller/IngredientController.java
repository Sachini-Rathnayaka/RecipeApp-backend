package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.IngredientDto;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.model.Ingredient;
import com.hasthiyait.recipeapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/getAllIngredients")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<IngredientDto>>> getAllIngredients(){
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully retrieved all ingredients");
        ApiResponse<List<IngredientDto>> response = new ApiResponse<>(status, this.ingredientService.listAllIngredients());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addIngredient")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<Ingredient>> addIngredient(@RequestBody IngredientDto ingredientDto){
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully added ingredient");
        ApiResponse<Ingredient> response = new ApiResponse<>(status, this.ingredientService.saveIngredient(ingredientDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
