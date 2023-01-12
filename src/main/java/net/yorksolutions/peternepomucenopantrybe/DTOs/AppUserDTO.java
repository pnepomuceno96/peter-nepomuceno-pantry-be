package net.yorksolutions.peternepomucenopantrybe.DTOs;


import java.util.Optional;

public class AppUserDTO {
    public Optional<Long> id;
    public String username;
    public String password;
    public Iterable<RecipeDTO> recipes;

}
