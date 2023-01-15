package net.yorksolutions.peternepomucenopantrybe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Cascade(CascadeType.ALL)
    private Set<Ingredient> ingredients;
    // MAKE SURE ITEM DOESNT GET DELETED WHEN YOU DELETE A RELATED RECIPE!!!

    private String steps;

    private Double totalWeight;

    private Long totalCalories;

    @JsonIgnoreProperties("recipes")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(CascadeType.DETACH)
    @OneToOne
    private AppUser user;

    public Recipe(Long id, String name, String description, String image, String steps, Double totalWeight, Long totalCalories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.steps = steps;
        this.totalWeight = totalWeight;
        this.totalCalories = totalCalories;
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

    public Long getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Long totalCalories) {
        this.totalCalories = totalCalories;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
