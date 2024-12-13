package com.hasthiyait.recipeapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="SHOPPINGLIST_INGREDIENT")
public class ShoppingListIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

}
