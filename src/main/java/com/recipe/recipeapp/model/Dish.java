package com.hasthiyait.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name ="dish")
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dish_id;
    private String dish_name;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "dish",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recipe> recipes;

    public Dish(String dishName) {
        this.dish_name= dishName;
    }
}