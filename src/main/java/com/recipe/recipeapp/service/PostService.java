package com.hasthiyait.recipeapp.service;


import com.hasthiyait.recipeapp.dao.request.PostDto;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Long userId, Long recipeId) ;

    //update
    PostDto updatePost(PostDto postDto, Long postId);

    //delete
    void deletePost(Long postId);

    //get all posts
    List<PostDto> getAllPosts();

    //get a single post
    PostDto getPostById(Long postId);

    //get all posts by recipe
    List<PostDto> getAllPostsByRecipe(Long recipeId);

    //get all posts by user
    List<PostDto> getAllPostsByUser(Long userId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
