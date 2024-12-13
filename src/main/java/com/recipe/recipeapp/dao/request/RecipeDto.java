package com.hasthiyait.recipeapp.dao.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecipeDto {

    private Long recipeId;
    private String title;
    private int commentCount;

}
