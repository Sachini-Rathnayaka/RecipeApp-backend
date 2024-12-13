package com.hasthiyait.recipeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column( name = "post_title", length = 1000, nullable = false)
    private String title;


    private String image;
    private Date addedDate;
    private int likeCount;
    private int commentCount;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();

    public void addComment(Comment comment) {
        if (comment != null) {
            comments.add(comment);
            comment.setPost(this);
            commentCount++; // Increment commentCount when a comment is added
        }
    }
    public void removeComment(Comment comment) {
        if (comment != null && comments.remove(comment)) {
            comment.setPost(null);
            commentCount--; // Decrement commentCount when a comment is removed
        }
    }



}

