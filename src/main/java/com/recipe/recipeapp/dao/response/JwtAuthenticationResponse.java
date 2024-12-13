package com.hasthiyait.recipeapp.dao.response;

import com.hasthiyait.recipeapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private String username;
    private Role role;
}
