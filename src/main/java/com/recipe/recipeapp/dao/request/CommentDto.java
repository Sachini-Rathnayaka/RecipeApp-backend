package com.hasthiyait.recipeapp.dao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {


    @NotBlank(message = "Text cannot be blank")
    private String text;
    private Date addedDate;
    private int likeCount;
    private UserDto user;
    private int commentId;

    private List<CommentDto> replyComments;
}
