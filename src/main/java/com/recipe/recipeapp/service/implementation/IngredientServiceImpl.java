package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.IngredientDto;
import com.hasthiyait.recipeapp.model.Ingredient;
import com.hasthiyait.recipeapp.repository.IngredientRepository;
import com.hasthiyait.recipeapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<IngredientDto> listAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(this::IngredientToDto).collect(Collectors.toList());
    }

    @Override
    public Ingredient saveIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = this.IngredientFromDto(ingredientDto);
        return this.ingredientRepository.save(ingredient);
    }

    public IngredientDto IngredientToDto(Ingredient ingredient) {
        return this.modelMapper.map(ingredient, IngredientDto.class);
    }

    public Ingredient IngredientFromDto(IngredientDto ingredientDto) {
        return this.modelMapper.map(ingredientDto, Ingredient.class);
    }
}
