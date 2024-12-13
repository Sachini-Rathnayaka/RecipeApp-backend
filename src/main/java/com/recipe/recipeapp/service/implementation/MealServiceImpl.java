package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.MealDto;
import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.dao.request.RecipeDto;
import com.hasthiyait.recipeapp.exception.ResourceNotFoundException;
import com.hasthiyait.recipeapp.model.*;
import com.hasthiyait.recipeapp.repository.MealRepository;
import com.hasthiyait.recipeapp.repository.RecipeRepository;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.MealService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    private final RecipeRepository recipeRepository;

    private final ModelMapper modelMapper;

    @Override
    public MealDto addRecipesToMeal(MealDto mealDto, Long userId, Long recipeId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Recipe recipe = this.recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe Id", recipeId));

        Meal meal = this.dtoToMeal(mealDto);
        meal.setType(mealDto.getType());

        // Format the date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(mealDto.getDate());
        try {
            meal.setDate(formatter.parse(formattedDate));
        } catch (ParseException e) {
            // Handle the parse exception if needed
            e.printStackTrace();
        }

        meal.setUser(user);
        meal.setRecipe(recipe);

        Meal newMeal = this.mealRepository.save(meal);
        return this.mealToDto(newMeal);
    }

    @Override
    public List<MealDto> getRecipesByMealType(MealType type, Date date) {
        List<Meal> meals = mealRepository.findByTypeAndDate(type, date);
        return meals.stream()
                .map(this::mealToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRecipeFromMeal(Long mealId) {
        Meal meal = this.mealRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "mealId", mealId));
        this.mealRepository.delete(meal);
    }


    public Meal dtoToMeal(MealDto mealDto) {
        return this.modelMapper.map(mealDto, Meal.class);
    }

    public MealDto mealToDto(Meal meal) {
        return this.modelMapper.map(meal, MealDto.class);
    }

}
