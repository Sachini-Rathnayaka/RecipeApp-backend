package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category,Integer> {
   // Optional<Category> findById(Integer id);
}
