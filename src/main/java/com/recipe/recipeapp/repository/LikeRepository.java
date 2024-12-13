package com.hasthiyait.recipeapp.repository;

import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Like;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findByUserAndPost(User user, Post post);

    Optional<Like> findByUserAndComment(User user, Comment comment);

    List<Like> findByPost(Post post);

    List<Like> findByComment(Comment comment);
}
