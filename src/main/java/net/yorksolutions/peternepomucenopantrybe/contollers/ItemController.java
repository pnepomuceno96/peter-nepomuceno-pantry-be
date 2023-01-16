package net.yorksolutions.peternepomucenopantrybe.contollers;

import net.yorksolutions.peternepomucenopantrybe.DTOs.ItemDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.models.Recipe;
import net.yorksolutions.peternepomucenopantrybe.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Item> getAll() { return service.getAll();}

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        try {
            return service.getItemById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createItem(@RequestBody ItemDTO itemRequest) {
        try {
            service.createItem(itemRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody ItemDTO itemRequest) {
        try {
            service.updateItem(id, itemRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public void subtractIngredients(@RequestBody Recipe recipe) {
        try {
            service.subtractItems(recipe);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        try {
            service.deleteItemById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
