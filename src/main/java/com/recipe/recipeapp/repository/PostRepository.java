package com.hasthiyait.recipeapp.repository;


import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.Recipe;
import com.hasthiyait.recipeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);
    List<Post> findByRecipe(Recipe recipe);

    List<Post> findByTitleContaining(String title);

    List<Post> findAllByOrderByAddedDateDesc();

}
