package com.hasthiyait.recipeapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipe")
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
@Id
@GeneratedValue
private Long recipeId;
    private String title;
    private String description;

    private int commentCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Source> sources;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Instruction> instructions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dish dish;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "colection_id")
    @JsonIgnore
    private collection collection;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Meal> meals = new ArrayList<>();

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addComment(Comment comment) {
        if (comment != null) {
            comments.add(comment);
            comment.setRecipe(this);
            commentCount++; // Increment commentCount when a comment is added
        }
    }
    public void removeComment(Comment comment) {
        if (comment != null && comments.remove(comment)) {
            comment.setRecipe(null);
            commentCount--; // Decrement commentCount when a comment is removed
        }
    }

}
