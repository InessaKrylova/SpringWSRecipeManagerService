package com.recipemanager.datalayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "recipe_fkey"), name="recipe_id", nullable=false)
    private Recipe recipe;

    public Ingredient(String title, Double amount, Recipe recipe) {
        this.title = title;
        this.amount = amount;
        this.recipe = recipe;
    }
}
