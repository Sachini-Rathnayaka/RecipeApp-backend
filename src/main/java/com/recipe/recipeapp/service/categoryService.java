package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.categoryRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Category;
import java.util.List;
import java.util.Optional;

public interface categoryService {
    Category addNewCategory(categoryRequest categoryRequest);

    List<Category> getAllCategories() throws NotFountException;

    Optional<Category> deleteCategoryById(Integer id) throws NotFountException;


}
