package com.hasthiyait.recipeapp.dao.response;

import com.hasthiyait.recipeapp.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class collectionCardResponse {
    private String collection_name;
  private List<Recipe> recipeList;
    private Integer number_of_recipies;
}
