package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sourceRepository extends JpaRepository<Source,Integer> {
}
