package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dishRepository extends JpaRepository<Dish,Integer> {
}
