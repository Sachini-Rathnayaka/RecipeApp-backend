package com.hasthiyait.recipeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post_like")
@NoArgsConstructor
@Getter
@Setter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean liked;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

}
