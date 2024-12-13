package com.hasthiyait.recipeapp.controller;


import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.dao.request.LikeDto;
import com.hasthiyait.recipeapp.exception.ResourceNotFoundException;
import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.repository.CommentRepository;
import com.hasthiyait.recipeapp.repository.PostRepository;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    //Add like to a post or unlike it
    @SuppressWarnings("null")
    @PostMapping("/post/{postId}/user/{userId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<String>> likePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        String confirmationMessage = String.format("The user with ID %d was successfully unliked.", userId);
        String confirmationMessage2 = String.format("The user with ID %d was successfully liked.", userId);

        // Check if the user has already liked the post
        if (likeService.hasUserLikedPost(user, post)) {
            ApiResponse<String> apiResponse = new ApiResponse<>(new ApiResponse.Status(HttpStatus.OK.value(), "Post disliked successfully!"), confirmationMessage);
            this.likeService.dislikePost(user, post);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<String> apiResponse1 = new ApiResponse<>(new ApiResponse.Status(HttpStatus.OK.value(), "Post liked successfully!"), confirmationMessage2);
            this.likeService.likePost(user, post);
            return new ResponseEntity<>(apiResponse1, HttpStatus.OK);
        }
    }

    //Add like to a post or unlike it
    @PostMapping("/comment/{commentId}/user/{userId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<String>> likeComment(@PathVariable("commentId") Integer commentId, @PathVariable("userId") Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));

        String confirmationMessage = String.format("The user with ID %d was successfully unliked.", userId);
        String confirmationMessage2 = String.format("The user with ID %d was successfully liked.", userId);

        // Check if the user has already liked the post
        if (likeService.hasUserLikedComment(user, comment)) {
            ApiResponse<String> apiResponse = new ApiResponse<>(new ApiResponse.Status(HttpStatus.OK.value(), "Comment disliked successfully!"), confirmationMessage);
            this.likeService.dislikeComment(user, comment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<String> apiResponse1 = new ApiResponse<>(new ApiResponse.Status(HttpStatus.OK.value(), "Comment liked successfully!"), confirmationMessage2);
            this.likeService.likeComment(user, comment);
            return new ResponseEntity<>(apiResponse1, HttpStatus.OK);
        }
    }


    //get all likes to a specific post
    @GetMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<LikeDto>>> getLikesByPost(@PathVariable("postId") Long postId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<LikeDto>> allLikes = new ApiResponse<>(status, this.likeService.getLikesByPost(postId));
        return new ResponseEntity<>(allLikes, HttpStatus.OK);
    }

    //get all likes to a specific comment
    @GetMapping("/comment/{commentId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<LikeDto>>> getLikesByComment(@PathVariable("commentId") Integer commentId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<LikeDto>> allLikes = new ApiResponse<> (status, this.likeService.getLikesByComment(commentId));
        return new ResponseEntity<>(allLikes, HttpStatus.OK);
    }
}

