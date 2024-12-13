package com.hasthiyait.recipeapp.dao.request;

import com.hasthiyait.recipeapp.model.MealType;
import com.hasthiyait.recipeapp.model.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class MealDto {

    private Long mealId;

    @NotNull(message = "Date cannot be blank")
    private Date date;

    @NotNull(message = "MealType cannot be blank")
    private MealType type;

    private RecipeDto recipe;
    private UserDto user;
}
