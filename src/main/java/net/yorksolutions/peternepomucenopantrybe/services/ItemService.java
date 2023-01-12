package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.ItemDTO;
import net.yorksolutions.peternepomucenopantrybe.models.Item;
import net.yorksolutions.peternepomucenopantrybe.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Iterable<Item> getAll() {return itemRepo.findAll();}

    public Item getItemById(Long id) {return itemRepo.findById(id).orElse(null);}

    public void createItem(ItemDTO itemRequest) throws Exception {
        Optional<Item> itemOptional = itemRepo.findItemByName(itemRequest.name);
        if (itemOptional.isPresent())
            throw new Exception();

        Item item = new Item();
        item.setName(itemRequest.name);
        item.setImage(itemRequest.image);
        item.setMeasurement(itemRequest.measurement);
        item.setCalories(itemRequest.calories);
        item.setQuantity(itemRequest.quantity);
        item.setWeight(itemRequest.weight);
        itemRepo.save(item);
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
        item.setWeight(itemRequest.weight);
        item.setCalories(itemRequest.calories);
        item.setQuantity(itemRequest.quantity);

        itemRepo.save(item);
    }
}
