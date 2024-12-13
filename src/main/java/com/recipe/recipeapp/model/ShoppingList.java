package com.hasthiyait.recipeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SHOPPING_LIST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    boolean isChecked;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @ManyToMany
    @JoinTable(name = "shoppinglist_ingredient", joinColumns = @JoinColumn(name = "shopping_list_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

}
