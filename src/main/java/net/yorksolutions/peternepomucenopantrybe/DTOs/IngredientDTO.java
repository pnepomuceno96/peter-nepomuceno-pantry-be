package net.yorksolutions.peternepomucenopantrybe.DTOs;

import java.util.Optional;

public class IngredientDTO {
    public Optional<Long> id;
    public Long itemId;
    public String name;
    public String measurement;
    public Long quantity;
}
