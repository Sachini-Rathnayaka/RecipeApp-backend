package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);

    List<Comment> findByRecipe(Recipe recipe);

}
