package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class recipeRequest {
@NotEmpty(message = "Recipe name cant be empty")
private String recipe_name;
@NotEmpty(message = "please enter a description")
private String description;
@Valid
@NotNull(message = "source List is empty")
private List<sourceRequest> sourceRequestList;
@Valid
@NotNull(message = "instruction list is empty")
private List<instructionRequest> instructionRequestList;

@NotNull(message = "category id is empty")
private Integer category_id;
@NotNull(message = "dish id is empty")
private Integer dish_id;

@NotNull(message = "user id is compulsary")
private Long user_id;

@NotNull(message = "collection id is compulsary")
private Integer collection_id;

}





