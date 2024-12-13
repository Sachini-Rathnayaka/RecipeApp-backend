package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.IngredientDto;
import com.hasthiyait.recipeapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    List<IngredientDto> listAllIngredients();
    Ingredient saveIngredient(IngredientDto ingredientDto);
}
