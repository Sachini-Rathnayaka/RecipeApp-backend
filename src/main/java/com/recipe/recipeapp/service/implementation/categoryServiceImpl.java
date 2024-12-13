package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.categoryRequest;
import com.hasthiyait.recipeapp.exception.NotFountException;
import com.hasthiyait.recipeapp.model.Category;
import com.hasthiyait.recipeapp.repository.categoryRepository;
import com.hasthiyait.recipeapp.service.categoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@SuppressWarnings("null")
@Service
public class categoryServiceImpl implements categoryService {

  private final com.hasthiyait.recipeapp.repository.categoryRepository categoryRepository;

  public categoryServiceImpl(categoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category addNewCategory(categoryRequest categoryRequest) {
    Category category = new Category(categoryRequest.getCategory_name());
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> getAllCategories() throws NotFountException {
    List<Category> categoryList = categoryRepository.findAll();
    if (categoryList.isEmpty()) {
      throw new NotFountException("Category list is empty");
    } else {
      return categoryList;
    }
  }

  @Override
  public Optional<Category> deleteCategoryById(Integer id)
    throws NotFountException {
    Optional<Category> categoryLn = categoryRepository.findById(id);
    if (categoryLn.isEmpty()) {
      throw new NotFountException("Given ID is does not exist ");
    } else {
      categoryRepository.deleteById(id);
      return categoryLn;
    }
  }
}
