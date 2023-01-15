package net.yorksolutions.peternepomucenopantrybe.DTOs;

import net.yorksolutions.peternepomucenopantrybe.models.Item;

import java.util.Optional;

public class RecipeDTO {
    public Optional<Long> id;
    public String name;
    public String description;
    public String image;
    public Iterable<IngredientDTO> ingredients;
    public Iterable<String> steps;
    public Double totalWeight;
    public Integer totalCalories;
    public Long userId;
}
