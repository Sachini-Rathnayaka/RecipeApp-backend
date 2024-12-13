package com.hasthiyait.recipeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Comment")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String text;
    private int likeCount;
    private Date addedDate;



    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @ManyToOne
    private Recipe recipe;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private Set<Comment> replyComments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

}

