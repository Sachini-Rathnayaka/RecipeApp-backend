package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.dishRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Dish;

import java.util.List;
import java.util.Optional;

public interface dishService {
    Dish addNewDish(dishRequest dishRequest);

    List<Dish> getAllDish() throws NotFountException;

    Optional<Dish> deleteDishById(Integer id) throws NotFountException;
}
