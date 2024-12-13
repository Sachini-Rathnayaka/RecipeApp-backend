package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class dishRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String dish_name;

}
