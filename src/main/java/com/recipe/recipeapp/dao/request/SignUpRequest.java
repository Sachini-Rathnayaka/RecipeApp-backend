package com.hasthiyait.recipeapp.dao.request;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpRequest {
    private String email;
    private String password;
    private String role;
}
