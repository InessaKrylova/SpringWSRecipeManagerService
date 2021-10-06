package com.recipemanager.datalayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="time_in_minutes")
    private Integer timeInMinutes;

    @OneToMany(mappedBy="recipe", fetch = FetchType.EAGER)
    private List<Step> steps;
    @OneToMany(mappedBy="recipe", fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    public Recipe(String title, Integer timeInMinutes, List<Step> steps, List<Ingredient> ingredients) {
        this.title = title;
        this.timeInMinutes = timeInMinutes;
    }

    public Recipe(){}

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, title='%s', timeInMinutes='%d' min]",
                id, title, timeInMinutes);
    }
}
