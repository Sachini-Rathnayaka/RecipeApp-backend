package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.ChangePassword;
import com.hasthiyait.recipeapp.dao.request.SignInRequest;
import com.hasthiyait.recipeapp.dao.request.SignUpRequest;
import com.hasthiyait.recipeapp.dao.response.JwtAuthenticationResponse;
import com.hasthiyait.recipeapp.model.Role;

public interface AuthenticationService {
    Object signIn(SignInRequest request);

    Object signUp(SignUpRequest request, Role role);

    String changePassword(ChangePassword changePassword);
}
