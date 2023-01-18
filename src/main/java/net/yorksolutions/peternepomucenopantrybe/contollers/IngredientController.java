package net.yorksolutions.peternepomucenopantrybe.contollers;

import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import net.yorksolutions.peternepomucenopantrybe.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin
public class IngredientController {
    private final IngredientService service;
    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Ingredient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        try {
            return service.getIngredientById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //Only needed controller/delete mapping to clean ingredient db of old orphan data
    @DeleteMapping("/{id}")
    public void deleteIngredientById(@PathVariable Long id) {
        try {
            service.deleteIngredientById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(params = {"ingredients"})
    public void deleteIngredients(@RequestBody Iterable<Ingredient> ingredients) {
        try {
            service.deleteIngredients(ingredients);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public void deleteAll() {
        try {
            service.deleteAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
