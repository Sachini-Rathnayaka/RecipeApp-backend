package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.recipeRequest;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.exception.categoryNotFountExeption;
import com.hasthiyait.recipeapp.exception.dishNotFoundExeption;
import com.hasthiyait.recipeapp.model.Recipe;
import com.hasthiyait.recipeapp.service.recipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
public class recipeController {

    private final recipeService recipeService;

    @PostMapping("/add-recipe")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<Recipe>> addNewRecipe(@Valid @RequestBody recipeRequest recipeRequest) throws categoryNotFountExeption, dishNotFoundExeption {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "add student successfully");
        ApiResponse<Recipe> response = new ApiResponse<>(status, recipeService.addNewRecipe(recipeRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
  
    @GetMapping("/get-all-recipe/{offset}/{pageSize}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Page<Recipe>>> getAllRecipe(@PathVariable int offset, @PathVariable int pageSize) throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "add student successfully");
        ApiResponse<Page<Recipe>> response = new ApiResponse<>(status, recipeService.getAllRecipe(offset, pageSize));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Recipe>> getRecipeById(@PathVariable Integer id) throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Recipe fetch successfully");
        ApiResponse<Recipe> response = new ApiResponse<>(status, recipeService.getRecipeById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}