package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.IngredientDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.repositories.IngredientRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientService {
    private final IngredientRepo ingredientRepo;
    private final ItemRepo itemRepo;
    public IngredientService(IngredientRepo ingredientRepo, ItemRepo itemRepo) {
        this.ingredientRepo = ingredientRepo;
        this.itemRepo = itemRepo;
    }

    public Set<Ingredient> createIngredients(RecipeDTO recipeRequest) throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();

        for (IngredientDTO ingredientDTO: recipeRequest.ingredients) {
            Ingredient ingredient = new Ingredient();
            Optional<Item> itemOptional = itemRepo.findById(ingredientDTO.itemId);
            if (itemOptional.isEmpty())
                throw new Exception();

            Item item = itemOptional.get();

            ingredient.setItem(item);

            ingredient.setQuantity(ingredientDTO.quantity);
            ingredient.setTotalWeight(ingredientDTO.quantity * item.getWeight());

            ingredients.add(ingredient);
        }
        ingredientRepo.saveAll(ingredients);
        return ingredients;
    }

    public Set<Ingredient> updateIngredients(Iterable<IngredientDTO> ingredientRequests) throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientDTO ingredientRequest: ingredientRequests) {
            Ingredient ingredient = new Ingredient();

            Optional<Item> itemOptional = itemRepo.findById(ingredientRequest.itemId);
            if (itemOptional.isEmpty())
                throw new Exception();

            Item item = itemOptional.get();

            ingredient.setItem(item);

            //"How much of this ingredient do we need?"
            ingredient.setQuantity(ingredientRequest.quantity);
            ingredient.setTotalWeight(ingredientRequest.quantity * item.getWeight());

            Ingredient savedIngredient = ingredientRepo.save(ingredient);
            ingredients.add(savedIngredient);
        }
        return ingredients;
    }
}
