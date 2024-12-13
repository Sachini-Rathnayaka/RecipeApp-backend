package com.hasthiyait.recipeapp.dao.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
    private int id;
    private boolean liked;
    private UserDto user;

}
