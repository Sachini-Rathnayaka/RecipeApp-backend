package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.ShoppingListDto;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.model.ShoppingList;
import com.hasthiyait.recipeapp.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shoppingList")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<ShoppingList>> save(@RequestBody ShoppingListDto shoppingListDto, @RequestParam List<Long> ingredientIds) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
        ApiResponse<ShoppingList> response = new ApiResponse<>(status, this.shoppingListService.saveShoppingList(shoppingListDto, ingredientIds));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
