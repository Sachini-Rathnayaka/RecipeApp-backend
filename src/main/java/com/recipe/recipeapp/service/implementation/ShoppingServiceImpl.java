package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.ShoppingListDto;
import com.hasthiyait.recipeapp.model.Ingredient;
import com.hasthiyait.recipeapp.model.ShoppingList;
import com.hasthiyait.recipeapp.repository.IngredientRepository;
import com.hasthiyait.recipeapp.repository.ShoppingListRepository;
import com.hasthiyait.recipeapp.service.ShoppingListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ShoppingList saveShoppingList(ShoppingListDto shoppingListDto, List<Long> ingredientIds) {
        ShoppingList shoppingList = this.dtoToShoppingList(shoppingListDto);
        shoppingList.setCreatedDate(LocalDateTime.now());
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);
        shoppingList.setIngredients(new HashSet<>(ingredients));
        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList dtoToShoppingList(ShoppingListDto shoppingListDto) {
        return this.modelMapper.map(shoppingListDto, ShoppingList.class);
    }
}
