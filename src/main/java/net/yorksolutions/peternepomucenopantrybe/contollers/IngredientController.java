package net.yorksolutions.peternepomucenopantrybe.contollers;

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

    @DeleteMapping("/{id}")
    public void deleteIngredientById(@PathVariable Long id) {
        try {
            service.deleteIngredientById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
