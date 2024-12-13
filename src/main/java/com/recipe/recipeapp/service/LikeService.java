package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.LikeDto;
import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.User;

import java.util.List;


public interface LikeService {

    void likePost(User user, Post post);

    void dislikePost(User user, Post post);

    boolean hasUserLikedPost(User user, Post post);

    void likeComment(User user, Comment comment);

    void dislikeComment(User user, Comment comment);

    boolean hasUserLikedComment(User user, Comment comment);


    List<LikeDto> getLikesByPost(Long postId);

    List<LikeDto> getLikesByComment(Integer commentId);

}
