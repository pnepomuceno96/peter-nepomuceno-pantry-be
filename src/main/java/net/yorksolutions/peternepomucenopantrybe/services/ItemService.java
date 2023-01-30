package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.IngredientDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.ItemDTO;
import net.yorksolutions.peternepomucenopantrybe.DTOs.RecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.Ingredient;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.models.Recipe;
import net.yorksolutions.peternepomucenopantrybe.repositories.ItemRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemService {
    private final ItemRepo itemRepo;
    private final RecipeRepo recipeRepo;

    public ItemService(ItemRepo itemRepo, RecipeRepo recipeRepo) {
        this.itemRepo = itemRepo;
        this.recipeRepo = recipeRepo;
    }

    public Iterable<Item> getAll() {return itemRepo.findAll();}

    public Item getItemById(Long id) {return itemRepo.findById(id).orElse(null);}

    public void createItem(ItemDTO itemRequest) throws Exception {
        Optional<Item> itemOptional = itemRepo.findItemByName(itemRequest.name);
        if (itemOptional.isPresent()) {
            //update item quantity if it already exists in pantry
            itemRequest.quantity = itemRequest.quantity + itemOptional.get().getQuantity();
            updateItem(itemOptional.get().getId(), itemRequest);
        } else {

            Item item = new Item();
            item.setName(itemRequest.name);
            item.setImage(itemRequest.image);
            item.setMeasurement(itemRequest.measurement);
            item.setCalories((double) Math.round(itemRequest.calories *100)/100);
            item.setQuantity((double) Math.round(itemRequest.quantity *100)/100);
            item.setWeight((double) Math.round(itemRequest.weight *100)/100);
            itemRepo.save(item);
        }
    }

    public void deleteItemById(Long id) throws Exception {
        Optional<Item> itemOptional = itemRepo.findById(id);
        if (itemOptional.isEmpty())
            throw new Exception();

        itemRepo.deleteById(id);
    }

    public void updateItem(Long id, ItemDTO itemRequest) throws Exception {
        Optional<Item> itemOptional = itemRepo.findById(id);
        if (itemOptional.isEmpty())
            throw new Exception();

        Item item = itemOptional.get();
        item.setName(itemRequest.name);
        item.setImage(itemRequest.image);
        item.setMeasurement(itemRequest.measurement);
        if(itemRequest.weight == null || itemRequest.calories == null) {
            System.out.println("Calories or weight is null");
            throw new Exception();
        }
        item.setWeight((double) Math.round(itemRequest.weight * 100)/ 100);
        item.setCalories((double) Math.round(itemRequest.calories * 100)/ 100);
        if(itemRequest.quantity < 0) {
            throw new Exception();
        }
        item.setQuantity((double) Math.round(itemRequest.quantity * 100)/ 100);

        itemRepo.save(item);
    }

    public void subtractItems(Recipe recipe) throws Exception {
        Set<Item> itemsToUpdate = new HashSet<>();
        for (Ingredient ingredient: recipe.getIngredients()) {
            Optional<Item> itemOptional = itemRepo.findItemByName(ingredient.getName());
            if(itemOptional.isEmpty())
                throw new Exception();

            Item item = itemOptional.get();

            if(item.getQuantity() < ingredient.getQuantity())
                throw new Exception();

            Double newQuantity = item.getQuantity() - ingredient.getQuantity();
            item.setQuantity((double) Math.round(newQuantity * 100)/ 100);
            itemsToUpdate.add(item);
        }
        itemRepo.saveAll(itemsToUpdate);
    }
}
