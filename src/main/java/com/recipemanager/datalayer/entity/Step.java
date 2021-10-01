package com.recipemanager.datalayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="step_number")
    private Integer stepNumber;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "recipe_fkey"), name="recipe_id", nullable=false)
    private Recipe recipe;

    public Step(String title, Integer stepNumber, Recipe recipe) {
        this.title = title;
        this.stepNumber = stepNumber;
        this.recipe = recipe;
    }
}
