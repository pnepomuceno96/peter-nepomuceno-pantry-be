package net.yorksolutions.peternepomucenopantrybe.models;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //@Column(unique = true)
    private String name;

    private String image;

    // What measurement to we use for this item?
    // Ex: Quantity of 3 tsp of salt
    private String measurement;

    private Double weight;

    private Long calories;

    private Long quantity;

    public Item(Long id, String name, String image, String measurement, Double weight, Long calories, Long quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.measurement = measurement;
        this.weight = weight;
        this.calories = calories;
        this.quantity = quantity;
    }

    public Item() {
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
