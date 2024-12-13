package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface instructionRepository extends JpaRepository<Instruction,Integer> {
}
