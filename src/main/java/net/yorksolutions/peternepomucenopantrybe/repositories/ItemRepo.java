package net.yorksolutions.peternepomucenopantrybe.repositories;

import net.yorksolutions.peternepomucenopantrybe.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends CrudRepository<Item, Long> {
    Optional<Item> findItemByName(String name);
}
