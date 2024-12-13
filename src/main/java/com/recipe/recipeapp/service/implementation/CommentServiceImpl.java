package com.hasthiyait.recipeapp.service.implementation;


import com.hasthiyait.recipeapp.dao.request.CommentDto;
import com.hasthiyait.recipeapp.exception.ResourceNotFoundException;
import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.Recipe;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.repository.CommentRepository;
import com.hasthiyait.recipeapp.repository.PostRepository;
import com.hasthiyait.recipeapp.repository.RecipeRepository;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final RecipeRepository recipeRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public CommentDto createCommentToPost(CommentDto commentDto, Long postId, Long userId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id",postId));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id",userId));

        Comment comment = this.dtoToComment(commentDto);
        comment.setAddedDate(new Date());
        comment.setPost(post);
        comment.setUser(user);

        post.addComment(comment); // This will increment commentCount
        postRepository.save(post);

        Comment newComment = this.commentRepository.save(comment);
        return this.commentToDto(newComment);
    }

    @Override
    public CommentDto createReplyComment(CommentDto commentDto, Integer parentCommentId,Long userId) {
        Comment parentComment = this.commentRepository.findById(parentCommentId).orElseThrow(() -> new ResourceNotFoundException("Parent Comment", "comment Id", parentCommentId));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id",userId));

        Comment comment = this.dtoToComment(commentDto);
        comment.setAddedDate(new Date());
        comment.setParentComment(parentComment);
        comment.setUser(user);

        parentComment.getReplyComments().add(comment);
        this.commentRepository.save(parentComment);

        Comment newComment = this.commentRepository.save(comment);
        return this.commentToDto(newComment);
    }


    @Override
    public CommentDto createCommentToRecipe(CommentDto commentDto, Long recipeId, Long userId) {

        Recipe recipe = this.recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe Id",recipeId));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id",userId));

        Comment comment = this.dtoToComment(commentDto);
        comment.setAddedDate(new Date());
        comment.setRecipe(recipe);
        comment.setUser(user);

        recipe.addComment(comment); // This will increment commentCount
        recipeRepository.save(recipe);

        Comment newComment = this.commentRepository.save(comment);
        return this.commentToDto(newComment);
    }



    @Override
    public void deleteCommentFromPost(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));

        if (comment != null) {
            Post post = comment.getPost();
            if (post != null) {
                post.removeComment(comment); // This will decrement commentCount
                postRepository.save(post);
            }
            this.commentRepository.delete(comment);
        }

    }

    @Override
    public void deleteCommentFromRecipe(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));

        if (comment != null) {
            Recipe recipe = comment.getRecipe();
            if (recipe != null) {
                recipe.removeComment(comment); // This will decrement commentCount
                recipeRepository.save(recipe);
            }
            this.commentRepository.delete(comment);
        }

    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepository.findAll();
        return comments.stream().map(this::commentToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        List<Comment> comments = this.commentRepository.findByPost(post);
        return comments.stream().map(this::commentToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = this.recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe Id", recipeId));
        List<Comment> comments = this.commentRepository.findByRecipe(recipe);
        return comments.stream().map(this::commentToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));

        comment.setText(commentDto.getText());
        comment.setAddedDate(new Date());

        Comment updatedComment = this.commentRepository.save(comment);
        return this.commentToDto(updatedComment);
    }

    public Comment dtoToComment(CommentDto commentDto){
        return this.modelMapper.map(commentDto, Comment.class);
    }

    public CommentDto commentToDto(Comment comment){
        return this.modelMapper.map(comment, CommentDto.class);
    }
}
