package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class sourceRequest {
@NotBlank(message = "source_name is empty")
    private String source_name;
@NotBlank(message = "utl ia epmty")
    private String url;
}
