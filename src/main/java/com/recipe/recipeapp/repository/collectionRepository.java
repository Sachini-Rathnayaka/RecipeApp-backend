package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface collectionRepository extends JpaRepository<collection, Integer> {
}
