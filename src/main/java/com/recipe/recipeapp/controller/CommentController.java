package com.hasthiyait.recipeapp.controller;


import com.hasthiyait.recipeapp.dao.request.CommentDto;
import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //Add a comment to post
    @PostMapping("/post/{postId}/user/{userId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<CommentDto>> createCommentToPost(@RequestBody CommentDto commentDto, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {

        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Comment created Successfully!");
        ApiResponse<CommentDto> createdComment = new ApiResponse<>(status, this.commentService.createCommentToPost(commentDto, postId, userId));
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PostMapping("/reply/{parentCommentId}/user/{userId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<CommentDto>> createReplyComment(@RequestBody CommentDto commentDto, @PathVariable("parentCommentId") Integer parentCommentId,@PathVariable("userId") Long userId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Reply comment created Successfully!");
        ApiResponse<CommentDto> createdComment = new ApiResponse<>(status, this.commentService.createReplyComment(commentDto, parentCommentId,userId));
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }



    //Add a comment to recipe
    @PostMapping("/recipe/{recipeId}/user/{userId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<CommentDto>> createCommentToRecipe(@RequestBody CommentDto commentDto, @PathVariable("recipeId") Long recipeId, @PathVariable("userId") Long userId) {

        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Comment created Successfully!");
        ApiResponse<CommentDto> createdComment = new ApiResponse<>(status, this.commentService.createCommentToRecipe(commentDto, recipeId, userId));
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    //Delete a comment from post
    @DeleteMapping("/delete-post/{commentId}")
   @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<String>> deleteCommentFromPost(@PathVariable("commentId") Integer commentId) {
        String confirmationMessage = String.format("The comment with ID %d was successfully deleted.", commentId);

        ApiResponse<String> apiResponse = new ApiResponse<>(
                new ApiResponse.Status(HttpStatus.OK.value(), "Comment deleted successfully!"),
                confirmationMessage
        );

        this.commentService.deleteCommentFromPost(commentId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //Delete a comment from recipe
    @DeleteMapping("/delete-recipe/{commentId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<String>> deleteCommentFromRecipe(@PathVariable("commentId") Integer commentId) {
        String confirmationMessage = String.format("The comment with ID %d was successfully deleted.", commentId);

        ApiResponse<String> apiResponse = new ApiResponse<>(
                new ApiResponse.Status(HttpStatus.OK.value(), "Comment deleted successfully!"),
                confirmationMessage
        );

        this.commentService.deleteCommentFromRecipe(commentId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //display all comments from post
    @GetMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsByPost(@PathVariable("postId") Long postId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<CommentDto>> allComments = new ApiResponse<>(status, this.commentService.getCommentsByPost(postId));
        return new ResponseEntity<>(allComments, HttpStatus.OK);

    }

    //display all comments from recipe
    @GetMapping("/recipe/{recipeId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsByRecipe(@PathVariable("recipeId") Long recipeId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<CommentDto>> allComments = new ApiResponse<>(status, this.commentService.getCommentsByRecipe(recipeId));
        return new ResponseEntity<>(allComments, HttpStatus.OK);

    }

    @PutMapping("/update/{commentId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<CommentDto>> updateComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("commentId") Integer commentId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully updated!");
        ApiResponse<CommentDto> response = new ApiResponse<>(status, this.commentService.updateComment(commentDto, commentId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//        @GetMapping("/")
//        public ResponseEntity<List<CommentDto>> getAllComments()  {
//            List<CommentDto> allComments = this.commentService.getAllComments();
//            return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);
//
//        }

    //get all comments to a specific post
}
