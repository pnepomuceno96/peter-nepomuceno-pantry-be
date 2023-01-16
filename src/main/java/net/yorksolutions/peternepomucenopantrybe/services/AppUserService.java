package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.AppUserDTO;
import net.yorksolutions.peternepomucenopantrybe.models.AppUser;
import net.yorksolutions.peternepomucenopantrybe.repositories.AppUserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public Iterable<AppUser> getAll() { return appUserRepo.findAll(); }

    public AppUser getUserByUsernameAndPassword(String username, String password) {
        return appUserRepo.findAppUserByUsernameAndPassword(username, password).orElse(null);
    }

    public AppUser getUserById(Long id) {
        return appUserRepo.findById(id).orElse(null);
    }

    public void createAppUser(AppUserDTO appUserRequest) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findAppUserByUsername(appUserRequest.username);
        if (appUserOptional.isPresent())
            throw new Exception();

        AppUser appUser = new AppUser();
        appUser.setUsername(appUserRequest.username);
        appUser.setPassword(appUserRequest.password);
        appUserRepo.save(appUser);
    }

    public void deleteAppUserById(Long id) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);
        if (appUserOptional.isEmpty())
            throw new Exception();

        appUserRepo.deleteById(id);
    }

    public void updateAppUser(Long id, AppUserDTO appUserRequest) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);
        if (appUserOptional.isEmpty())
            throw new Exception();

        AppUser appUser = appUserOptional.get();
        appUser.setUsername(appUserRequest.username);
        appUser.setPassword(appUserRequest.password);

        appUserRepo.save(appUser);
    }


}
