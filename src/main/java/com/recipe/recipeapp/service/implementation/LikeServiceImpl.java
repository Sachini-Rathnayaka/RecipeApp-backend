package com.hasthiyait.recipeapp.service.implementation;



import com.hasthiyait.recipeapp.dao.request.LikeDto;
import com.hasthiyait.recipeapp.model.Comment;
import com.hasthiyait.recipeapp.model.Like;
import com.hasthiyait.recipeapp.model.Post;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.repository.CommentRepository;
import com.hasthiyait.recipeapp.repository.LikeRepository;
import com.hasthiyait.recipeapp.repository.PostRepository;
import com.hasthiyait.recipeapp.exception.ResourceNotFoundException;
import com.hasthiyait.recipeapp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;


    @Override
    public void likePost(User user, Post post) {
        handleLikeDislikePost(user, post);
        post.setLikeCount(post.getLikeCount() + 1); // Increment likeCount
        postRepository.save(post);
    }

    @SuppressWarnings("null")
    @Override
    public void dislikePost(User user, Post post) {
        // Check if the user has already liked the post
        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            // If the user has liked the post, remove the like
            likeRepository.delete(existingLike.get());
            post.setLikeCount(post.getLikeCount() - 1); // Decrement likeCount
            postRepository.save(post);
        }
    }

    private void handleLikeDislikePost(User user, Post post) {
        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            Like likeDto = existingLike.get();
            likeDto.setLiked(true);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            like.setLiked(true);
            likeRepository.save(like);
        }
    }

    @Override
    public boolean hasUserLikedPost(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post)
                .map(Like::isLiked)
                .orElse(false);
    }

    @Override
    public void likeComment(User user, Comment comment) {
        handleLikeDislikeComment(user, comment);
        comment.setLikeCount(comment.getLikeCount() + 1); // Increment likeCount
        commentRepository.save(comment);
    }

    @Override
    public void dislikeComment(User user, Comment comment) {
        // Check if the user has already liked the post
        Optional<Like> existingLike = likeRepository.findByUserAndComment(user, comment);

        if (existingLike.isPresent()) {
            // If the user has liked the post, remove the like
            likeRepository.delete(existingLike.get());
            comment.setLikeCount(comment.getLikeCount() - 1); // Decrement likeCount
            commentRepository.save(comment);
        }
    }

    private void handleLikeDislikeComment(User user, Comment comment) {
        Optional<Like> existingLike = likeRepository.findByUserAndComment(user, comment);

        if (existingLike.isPresent()) {
            Like likeDto = existingLike.get();
            likeDto.setLiked(true);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setComment(comment);
            like.setLiked(true);
            likeRepository.save(like);
        }
    }

    @Override
    public boolean hasUserLikedComment(User user, Comment comment) {
        return likeRepository.findByUserAndComment(user, comment)
                .map(Like::isLiked)
                .orElse(false);
    }


    @Override
    public List<LikeDto> getLikesByPost(Long postId) {
        @SuppressWarnings("null")
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        List<Like> likes = this.likeRepository.findByPost(post);
        return likes.stream().map(this::likeToDto).collect(Collectors.toList());

    }

    @Override
    public List<LikeDto> getLikesByComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));
        List<Like> likes = this.likeRepository.findByComment(comment);
        return likes.stream().map(this::likeToDto).collect(Collectors.toList());

    }

    public Like dtoToLike(LikeDto likeDto){
        return this.modelMapper.map(likeDto, Like.class);
    }

    public LikeDto likeToDto(Like like){
        return this.modelMapper.map(like, LikeDto.class);
    }
}

