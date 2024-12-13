package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
