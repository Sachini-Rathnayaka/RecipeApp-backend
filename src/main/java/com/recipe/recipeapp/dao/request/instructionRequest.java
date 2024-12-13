package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class instructionRequest {
    @NotNull(message = "step number is empty")
    private Integer step_number;
    @NotBlank(message = "description is empty")
    private String description;
}
