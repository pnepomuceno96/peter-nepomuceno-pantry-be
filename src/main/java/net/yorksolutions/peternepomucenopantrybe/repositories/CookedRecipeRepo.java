package net.yorksolutions.peternepomucenopantrybe.repositories;

import net.yorksolutions.peternepomucenopantrybe.models.CookedRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookedRecipeRepo extends CrudRepository<CookedRecipe, Long> {
}
