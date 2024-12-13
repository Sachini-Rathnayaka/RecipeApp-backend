package com.hasthiyait.recipeapp.dao.request;

import com.hasthiyait.recipeapp.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ShoppingListDto {
    private String shoppingListName;
    private boolean isChecked;
}
