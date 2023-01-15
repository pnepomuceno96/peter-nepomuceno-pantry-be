package net.yorksolutions.peternepomucenopantrybe.contollers;

import net.yorksolutions.peternepomucenopantrybe.DTOs.CookedRecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.CookedRecipe;
import net.yorksolutions.peternepomucenopantrybe.services.CookedRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cookedrecipes")
@CrossOrigin
public class CookedRecipeController {
    private final CookedRecipeService service;
    public CookedRecipeController(CookedRecipeService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<CookedRecipe> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void createCookedRecipe(@RequestBody CookedRecipeDTO cookedRecipe) {
        try {
            service.createCookedRecipe(cookedRecipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCookedRecipeById(@PathVariable Long id) {
        try {
            service.deleteCookedRecipeById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateCookedRecipe(@PathVariable Long id, @RequestBody CookedRecipeDTO cookedRecipe) {
        try {
            service.updateCookedRecipe(id, cookedRecipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
