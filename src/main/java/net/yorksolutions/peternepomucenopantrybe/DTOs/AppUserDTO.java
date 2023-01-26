package net.yorksolutions.peternepomucenopantrybe.DTOs;


import java.util.Optional;
import java.util.UUID;

public class AppUserDTO {
    //public Optional<Long> id;

    public Optional<UUID> id;
    public String username;
    public String password;
    public Iterable<RecipeDTO> recipes;

}
