package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.IngredientDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.CookedRecipe;
import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.repositories.IngredientRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
public class IngredientService {
    private final IngredientRepo ingredientRepo;
    private final ItemRepo itemRepo;
    public IngredientService(IngredientRepo ingredientRepo, ItemRepo itemRepo) {
        this.ingredientRepo = ingredientRepo;
        this.itemRepo = itemRepo;
    }

    public Iterable<Ingredient> getAll() {
        return ingredientRepo.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepo.findById(id).orElse(null);
    }
//    public List<Ingredient> getAllAsList() {
//
//        return ingredientRepo.findAll();
//    }

    public Set<Ingredient> createIngredients(RecipeDTO recipeRequest) throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();

        for (IngredientDTO ingredientDTO: recipeRequest.ingredients) {
            Ingredient ingredient = new Ingredient();
            Optional<Item> itemOptional = itemRepo.findItemByName(ingredientDTO.name);
            if (itemOptional.isEmpty())
                throw new Exception();

            Item item = itemOptional.get();

            //ingredient.setItemNo(ingredientDTO.itemNo);
            ingredient.setName(item.getName());
            ingredient.setMeasurement(item.getMeasurement());

            ingredient.setQuantity(ingredientDTO.quantity);
            ingredient.setTotalWeight(ingredientDTO.quantity * item.getWeight());
            System.out.println("ingredient Weight: " + ingredient.getTotalWeight());
            ingredient.setTotalCalories(ingredientDTO.quantity * item.getCalories());
            ingredients.add(ingredient);
        }
        ingredientRepo.saveAll(ingredients);
        return ingredients;
    }

    public Set<Ingredient> updateIngredients(Iterable<IngredientDTO> ingredientRequests) throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientDTO ingredientRequest: ingredientRequests) {
            Ingredient ingredient = new Ingredient();

            Optional<Item> itemOptional = itemRepo.findItemByName(ingredientRequest.name);
            if (itemOptional.isEmpty())
                throw new Exception();

            Item item = itemOptional.get();

            //ingredient.setItemNo(ingredientRequest.itemNo);
            ingredient.setName(item.getName());
            ingredient.setMeasurement(item.getMeasurement());
            //"How much of this ingredient do we need?"
            ingredient.setQuantity(ingredientRequest.quantity);
            ingredient.setTotalWeight(ingredientRequest.quantity * item.getWeight());
            ingredient.setTotalCalories(ingredientRequest.quantity * item.getCalories());
            Ingredient savedIngredient = ingredientRepo.save(ingredient);
            ingredients.add(savedIngredient);
        }

        ingredientRepo.saveAll(ingredients);
        return ingredients;

    }

    public void deleteIngredientById(Long id) throws Exception{
        Optional<Ingredient> ingredientOptional = ingredientRepo.findById(id);
        if (ingredientOptional.isEmpty())
            throw new Exception();

        ingredientRepo.deleteById(id);
    }

    public void deleteAll() {
        ingredientRepo.deleteAll(this.getAll());
    }

    public void deleteIngredients(Iterable<Ingredient> ingredients) {
        System.out.println("ingredients : "+ ingredients);
        ingredientRepo.deleteAll(ingredients);
    }
}
