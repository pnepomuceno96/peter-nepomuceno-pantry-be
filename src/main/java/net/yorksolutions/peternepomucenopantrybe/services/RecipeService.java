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

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
        //recipe.setUser(appUser);


        String steps = String.join("\n| ", recipeRequest.steps);
        if(steps.equals("null"))
            throw new Exception();

        recipe.setSteps(steps);

        Set<Ingredient> ingredients = ingredientService.createIngredients(recipeRequest);
        recipe.setIngredients(ingredients);
        Double totalWeight = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalWeight += ingredient.getTotalWeight();
        }
        recipe.setTotalWeight(totalWeight);

        Double totalCalories = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalCalories += ingredient.getTotalCalories();
        }
        recipe.setTotalCalories(totalCalories);


        //Recipe savedRecipe = recipeRepo.save(recipe);

        appUser.getRecipes().add(recipe);
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
        String steps = String.join("\n| ", recipeRequest.steps);
        if(steps.equals(""))
            throw new Exception();

        recipe.setSteps(steps);

        //Iterable<Ingredient> oldIngredients = recipe.getIngredients();

        Set<Ingredient> ingredients = ingredientService.updateIngredients(recipeRequest.ingredients);
        Double totalWeight = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalWeight += ingredient.getTotalWeight();
        }
        recipe.setTotalWeight(totalWeight);

        Double totalCalories = 0.0;
        for (Ingredient ingredient: ingredients) {
            totalCalories += ingredient.getTotalCalories();
        }
        recipe.setTotalCalories(totalCalories);
        recipe.setIngredients(ingredients);
        recipeRepo.save(recipe);
        //System.out.println(oldIngredients);


//        for(Ingredient i: oldIngredients) {
//            ingredientService.deleteIngredientById(i.getId());
//        }



    }

    public void deleteRecipeById(UUID userId, Long recipeId) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(userId);
        if (appUserOptional.isEmpty())
            throw new Exception();

        AppUser appUser = appUserOptional.get();

        Optional<Recipe> recipeOptional = recipeRepo.findById(recipeId);
        if (recipeOptional.isEmpty())
            throw new Exception();

        Recipe recipeToDelete = null;
        for (Recipe recipe: appUser.getRecipes())  {
            if(recipe.getId() == recipeId) {
                recipeToDelete = recipe;
            }
        }

        if(recipeToDelete != null) {
            appUser.getRecipes().remove(recipeToDelete);
        }

        appUserRepo.save(appUser);

        //recipeRepo.deleteById(recipeId);
    }
}
