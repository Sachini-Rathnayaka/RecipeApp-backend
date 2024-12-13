package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.dishRequest;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Dish;
import com.hasthiyait.recipeapp.service.dishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dish")
public class dishController {

    private final dishService dishService;

    @Autowired
    public dishController(dishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("add-new-dish")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Dish>> adddNewDish(@Valid @RequestBody dishRequest dishRequest) {
        ApiResponse.Status status = new ApiResponse.Status(200, "add category successfully");
        ApiResponse<Dish> response = new ApiResponse<>(status, dishService.addNewDish(dishRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all-dish")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDish() throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(200, "add category successfully");
        ApiResponse<List<Dish>> response = new ApiResponse<>(status, dishService.getAllDish());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete-dish-by-ID/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Optional<Dish>>> deleteDishById(@PathVariable Integer id) throws NotFountException {
        System.out.println("testing testing");
        ApiResponse.Status status = new ApiResponse.Status(200, "deleted the dish related for given ID");
        ApiResponse<Optional<Dish>> response = new ApiResponse<>(status, dishService.deleteDishById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
