package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long postId;

    @NotNull(message = "Title cannot be blank")
    private String title;
    private String image;
    private Date addedDate;
    private int commentCount;
    private int likeCount;
    private RecipeDto recipe;
    private UserDto user;

}

