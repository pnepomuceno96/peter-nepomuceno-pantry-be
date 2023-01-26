package net.yorksolutions.peternepomucenopantrybe.repositories;

import net.yorksolutions.peternepomucenopantrybe.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, UUID> {
    Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);
    Optional<AppUser> findAppUserByUsername(String username);
}
