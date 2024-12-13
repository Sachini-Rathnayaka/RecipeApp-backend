package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.service.PostService;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    //create a post with a previous recipe
    @PostMapping("/user/{userId}/recipe/{recipeId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<PostDto>> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Long userId, @PathVariable("recipeId") Long recipeId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Post created Successfully!");
        ApiResponse<PostDto> response = new ApiResponse<>(status, this.postService.createPost(postDto, userId, recipeId));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //delete a post
    @DeleteMapping("/delete/{postId}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable("postId") Long postId) {
        String confirmationMessage = String.format("The post with ID %d was successfully deleted.", postId);
        ApiResponse<String> response = new ApiResponse<>(
                new ApiResponse.Status(HttpStatus.OK.value(), "Post deleted successfully!"),
                confirmationMessage
        );
        postService.deletePost(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update a post
    @PutMapping("/update/{postId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") Long postId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully updated!");
        ApiResponse<PostDto> response = new ApiResponse<>(status, this.postService.updatePost(postDto, postId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get a post by specific user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<PostDto>>> getPostsByUser(@PathVariable("userId") Long userId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<PostDto>> response = new ApiResponse<>(status, this.postService.getAllPostsByUser(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get a post by specific recipe
    @GetMapping("/recipe/{recipeId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<PostDto>>> getPostsByRecipe(@PathVariable("recipeId") Long recipeId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<PostDto>> response = new ApiResponse<>(status, this.postService.getAllPostsByRecipe(recipeId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //display all posts
    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts() {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<PostDto>> response = new ApiResponse<>(status, this.postService.getAllPosts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //display a single post
    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable("postId") Long postId) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<PostDto> response = new ApiResponse<>(status, this.postService.getPostById(postId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //search posts by keywords in title
    @GetMapping("/search/{keywords}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ApiResponse<List<PostDto>>> searchPosts(@PathVariable("keywords") String keywords) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<PostDto>> response = new ApiResponse<>(status, this.postService.searchPosts(keywords));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
