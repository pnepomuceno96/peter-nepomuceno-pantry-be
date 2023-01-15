package net.yorksolutions.peternepomucenopantrybe.DTOs;

import java.util.Optional;

public class CookedRecipeDTO {
    public Optional<Long> id;
    public String name;
    public String image;
    public String description;
    public Long calories;
    public Double weight;
}
