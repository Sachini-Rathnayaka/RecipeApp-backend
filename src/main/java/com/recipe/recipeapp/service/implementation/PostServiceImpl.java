package com.hasthiyait.recipeapp.service.implementation;


import com.hasthiyait.recipeapp.dao.request.PostDto;
import com.hasthiyait.recipeapp.exception.ResourceNotFoundException;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.Recipe;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.repository.PostRepository;
import com.hasthiyait.recipeapp.repository.RecipeRepository;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final RecipeRepository recipeRepository;

    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long recipeId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Recipe recipe = this.recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe Id", recipeId));

        Post post = this.dtoToPost(postDto);
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setRecipe(recipe);

        Post newPost = this.postRepository.save(post);
        return this.postToDto(newPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setImage(postDto.getImage());
        post.setAddedDate(new Date());

        Post updatedPost = this.postRepository.save(post);
        return this.postToDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepository.findAllByOrderByAddedDateDesc();
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        return this.postToDto(post);
    }

    @Override
    public List<PostDto> getAllPostsByRecipe(Long recipeId) {
        Recipe recipe = this.recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe Id", recipeId));
        List<Post> posts = this.postRepository.findByRecipe(recipe);

        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        List<Post> posts = this.postRepository.findByUser(user);

        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.findByTitleContaining(keyword);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    public Post dtoToPost(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    public PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }
}
