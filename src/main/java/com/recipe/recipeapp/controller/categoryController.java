package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.categoryRequest;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Category;
import com.hasthiyait.recipeapp.service.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class categoryController {
    private final categoryService categoryService;

    @Autowired
    public categoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("add-new-category")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Category>> addNewCategory(@Valid @RequestBody categoryRequest categoryRequest) {
        ApiResponse.Status status = new ApiResponse.Status(200, "add category successfully");
        ApiResponse<Category> response = new ApiResponse<>(status, categoryService.addNewCategory(categoryRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all-categories")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() throws NotFountException {
        ApiResponse.Status status = new ApiResponse.Status(200, "received all categories successfully");
        ApiResponse<List<Category>> response = new ApiResponse<>(status, categoryService.getAllCategories());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete-category-by-ID/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Optional<Category>>> deleteCategoryById(@PathVariable Integer id) throws NotFountException {
        System.out.println("testing testing");
        ApiResponse.Status status = new ApiResponse.Status(200, "deleted the category related for given ID");
        ApiResponse<Optional<Category>> response = new ApiResponse<>(status, categoryService.deleteCategoryById(id));
        ResponseEntity<ApiResponse<Optional<Category>>> apiResponseResponseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return apiResponseResponseEntity;
    }
}
