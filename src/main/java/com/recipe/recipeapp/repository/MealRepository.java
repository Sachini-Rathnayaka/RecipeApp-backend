package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Meal;
import com.hasthiyait.recipeapp.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByType(MealType mealType);

    List<Meal> findByTypeAndDate(MealType mealType, Date date);
}