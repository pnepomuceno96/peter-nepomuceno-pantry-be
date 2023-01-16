package net.yorksolutions.peternepomucenopantrybe.contollers;

import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.Recipe;
import net.yorksolutions.peternepomucenopantrybe.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Recipe> getAll() { return service.getAll();}

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        try {
            return service.getRecipeById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createRecipe(@RequestBody RecipeDTO recipe) {
        try {
            service.createRecipe(recipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipe) {
        try {
            service.updateRecipe(id, recipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/{recipeId}")
    public void deleteRecipeById(@PathVariable Long userId, @PathVariable Long recipeId) {
        try {
            service.deleteRecipeById(userId, recipeId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
