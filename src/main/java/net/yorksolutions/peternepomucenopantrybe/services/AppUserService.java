package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.AppUserDTO;
import net.yorksolutions.peternepomucenopantrybe.models.AppUser;
import net.yorksolutions.peternepomucenopantrybe.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;


    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public Iterable<AppUser> getAll() { return appUserRepo.findAll(); }

    public AppUser getUserByUsernameAndPassword(String username, String password) throws Exception {
        Optional<AppUser> appUserOptional =  appUserRepo.findAppUserByUsernameAndPassword(username, password);
        if(appUserOptional.isEmpty())
            throw new Exception();

        return appUserOptional.get();
    }

    public AppUser getUserByUsername(String username) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findAppUserByUsername(username);
        if(appUserOptional.isEmpty())
            throw new Exception();

        return appUserOptional.get();
    }

    public AppUser getUserById(UUID id) {
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

    public void deleteAppUserById(UUID id) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);
        if (appUserOptional.isEmpty())
            throw new Exception();

        appUserRepo.deleteById(id);
    }

    public void updateAppUser(UUID id, AppUserDTO appUserRequest) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);
        if (appUserOptional.isEmpty())
            throw new Exception();

        AppUser appUser = appUserOptional.get();
        Pattern pattern = Pattern.compile("^[\\w.@-]*$");
        Matcher matcher = pattern.matcher(appUserRequest.username);
        boolean validUsername = matcher.find();
        if(!validUsername) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }


        appUser.setUsername(appUserRequest.username);
        appUser.setPassword(appUserRequest.password);

        appUserRepo.save(appUser);
    }


}
