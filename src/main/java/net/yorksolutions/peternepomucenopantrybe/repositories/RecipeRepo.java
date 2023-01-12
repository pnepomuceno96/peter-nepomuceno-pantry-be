package net.yorksolutions.peternepomucenopantrybe.repositories;

import net.yorksolutions.peternepomucenopantrybe.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findRecipeByName(String name);
}
