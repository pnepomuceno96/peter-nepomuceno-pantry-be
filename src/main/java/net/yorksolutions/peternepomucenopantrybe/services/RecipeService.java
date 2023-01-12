package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.IngredientDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.AppUser;
import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.models.Recipe;
import net.yorksolutions.peternepomucenopantrybe.repositories.AppUserRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.IngredientRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {
    private final RecipeRepo recipeRepo;
    private final IngredientRepo ingredientRepo;
    private final AppUserRepo appUserRepo;
    private final IngredientService ingredientService;
    public RecipeService(RecipeRepo recipeRepo, AppUserRepo appUserRepo, IngredientRepo ingredientRepo, IngredientService ingredientService) {
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.ingredientService = ingredientService;
        this.appUserRepo = appUserRepo;
    }

    public Iterable<Recipe> getAll() { return recipeRepo.findAll();}

    public Recipe getRecipeById(Long id) {
        return recipeRepo.findById(id).orElse(null);
    }

    public void createRecipe(RecipeDTO recipeRequest) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(recipeRequest.userId);
        if (appUserOptional.isEmpty())
            throw new Exception();

        AppUser appUser = appUserOptional.get();


        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.name);
        recipe.setDescription(recipeRequest.description);
        recipe.setImage(recipeRequest.image);
        recipe.setUser(appUser);
        String steps = String.join("/n", recipeRequest.steps);
        recipe.setSteps(steps);

        Set<Ingredient> ingredients = ingredientService.createIngredients(recipeRequest);
        recipe.setIngredients(ingredients);
        Double totalWeight = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalWeight += ingredient.getTotalWeight();
        }
        recipe.setTotalWeight(totalWeight);
        Recipe savedRecipe = recipeRepo.save(recipe);

        appUser.getRecipes().add(savedRecipe);
        appUserRepo.save(appUser);
    }

    public void updateRecipe(Long id, RecipeDTO recipeRequest) throws Exception {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        if (recipeOptional.isEmpty())
            throw new Exception();

        Recipe recipe = recipeOptional.get();
        recipe.setName(recipeRequest.name);
        recipe.setDescription(recipeRequest.description);
        recipe.setImage(recipeRequest.image);
        String steps = String.join("/n", recipeRequest.steps);
        recipe.setSteps(steps);
        Set<Ingredient> ingredients = ingredientService.updateIngredients(recipeRequest.ingredients);
        Double totalWeight = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalWeight += ingredient.getTotalWeight();
        }
        recipe.setTotalWeight(totalWeight);
        recipe.setIngredients(ingredients);


    }

    public void deleteRecipeById(Long id) throws Exception {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        if (recipeOptional.isEmpty())
            throw new Exception();

        recipeRepo.deleteById(id);
    }
}