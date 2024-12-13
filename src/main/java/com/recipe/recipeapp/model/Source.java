package com.hasthiyait.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name ="source")
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer source_id;
    private String source_name;
    private String url;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="recipe_id")
    @JsonIgnore
    private Recipe recipe;

    public Source(String sourceName, String url) {
        this.source_name=sourceName;
        this.url=url;
    }
}
