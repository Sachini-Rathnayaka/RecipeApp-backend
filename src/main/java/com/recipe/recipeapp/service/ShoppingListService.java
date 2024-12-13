package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.ShoppingListDto;
import com.hasthiyait.recipeapp.model.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ShoppingListService {
    ShoppingList saveShoppingList(ShoppingListDto shoppingListDto, List<Long> ingredientIds);
}
