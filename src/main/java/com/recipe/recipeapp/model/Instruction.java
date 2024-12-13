package com.hasthiyait.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instruction")
@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instruction_id;
    private Integer step_number;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    public Instruction(Integer stepNumber, String description) {
        this.description = description;
        this.step_number = stepNumber;

    }

//    @ManyToOne
//    @JoinColumn(name = "recipe_id")
//    private Recipe recipe;

}
