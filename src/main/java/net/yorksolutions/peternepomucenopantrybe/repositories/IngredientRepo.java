package net.yorksolutions.peternepomucenopantrybe.repositories;

import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends CrudRepository<Ingredient, Long> {
}
