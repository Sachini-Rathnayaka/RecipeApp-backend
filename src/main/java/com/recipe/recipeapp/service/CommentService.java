package com.hasthiyait.recipeapp.service;

import com.hasthiyait.recipeapp.dao.request.CommentDto;
import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentDto createCommentToPost(CommentDto commentDto, Long postId, Long userId);

    CommentDto createReplyComment(CommentDto commentDto, Integer parentCommentId,Long userId);

    CommentDto createCommentToRecipe(CommentDto commentDto, Long recipeId, Long userId);



    void deleteCommentFromPost(Integer commentId);

    void deleteCommentFromRecipe(Integer commentId);

    CommentDto updateComment(CommentDto commentDto, Integer commentId);

    List<CommentDto> getAllComments();

    List<CommentDto> getCommentsByPost(Long postId);

    List<CommentDto> getCommentsByRecipe(Long recipeId);

}