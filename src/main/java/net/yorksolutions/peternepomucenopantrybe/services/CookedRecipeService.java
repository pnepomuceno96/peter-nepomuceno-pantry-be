package net.yorksolutions.peternepomucenopantrybe.services;

import net.yorksolutions.peternepomucenopantrybe.DTOs.CookedRecipeDTO;
import net.yorksolutions.peternepomucenopantrybe.models.AppUser;
import net.yorksolutions.peternepomucenopantrybe.models.CookedRecipe;
import net.yorksolutions.peternepomucenopantrybe.repositories.AppUserRepo;
import net.yorksolutions.peternepomucenopantrybe.repositories.CookedRecipeRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CookedRecipeService {
    private final CookedRecipeRepo cookedRecipeRepo;
    private final AppUserRepo appUserRepo;
    public CookedRecipeService(CookedRecipeRepo cookedRecipeRepo, AppUserRepo appUserRepo) {
        this.cookedRecipeRepo = cookedRecipeRepo;
        this.appUserRepo = appUserRepo;
    }

    public Iterable<CookedRecipe> getAll() { return cookedRecipeRepo.findAll();}

    public void createCookedRecipe(CookedRecipeDTO cookedRecipeRequest) {
        CookedRecipe cookedRecipe = new CookedRecipe();
        cookedRecipe.setName(cookedRecipeRequest.name);
        cookedRecipe.setImage(cookedRecipeRequest.image);
        cookedRecipe.setDescription(cookedRecipeRequest.description);
        cookedRecipe.setCalories((double) Math.round(cookedRecipeRequest.calories * 100) /100);
        cookedRecipe.setWeight((double) Math.round(cookedRecipeRequest.weight * 100) /100);

        cookedRecipeRepo.save(cookedRecipe);
    }

    public void deleteCookedRecipeById(Long id) throws Exception {
        Optional<CookedRecipe> cookedRecipeOptional = cookedRecipeRepo.findById(id);
        if (cookedRecipeOptional.isEmpty())
            throw new Exception();

        cookedRecipeRepo.deleteById(id);
    }

    public void updateCookedRecipe(Long id, CookedRecipeDTO cookedRecipeRequest) throws Exception {
        Optional<CookedRecipe> cookedRecipeOptional = cookedRecipeRepo.findById(id);
        if (cookedRecipeOptional.isEmpty())
            throw new Exception();

        CookedRecipe cookedRecipe = cookedRecipeOptional.get();
        cookedRecipe.setName(cookedRecipeRequest.name);
        cookedRecipe.setImage(cookedRecipeRequest.image);
        cookedRecipe.setDescription(cookedRecipeRequest.description);
//        cookedRecipe.setCalories(cookedRecipeRequest.calories);
//        cookedRecipe.setWeight(cookedRecipeRequest.weight);
        cookedRecipeRepo.save(cookedRecipe);
    }
}
