package net.yorksolutions.peternepomucenopantrybe.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    private String image;

    @OneToMany
    private Set<Ingredient> ingredients;
    // MAKE SURE ITEM DOESNT GET DELETED WHEN YOU DELETE A RELATED RECIPE!!!

    private String steps;

    private Double totalWeight;

    @OneToOne
    private AppUser user;

    public Recipe(Long id, String name, String description, String image, String steps, Double totalWeight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.steps = steps;
        this.totalWeight = totalWeight;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
