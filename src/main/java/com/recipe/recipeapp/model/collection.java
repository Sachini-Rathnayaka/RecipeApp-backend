package com.hasthiyait.recipeapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="collection")
public class collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;
    private String collectionName;
    private Integer collectionStatus;    // mekata danna 1 nam public 2 nam  private
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "collection",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Recipe> recipeList;


    public collection(String collectionName, Integer collectionStatus) {
        this.collectionName = collectionName;
        this.collectionStatus = collectionStatus;
    }
}
