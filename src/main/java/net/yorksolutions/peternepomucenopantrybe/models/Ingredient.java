package net.yorksolutions.peternepomucenopantrybe.models;

import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Item item;

    private Long quantity;
    private Double totalWeight;

    public Ingredient(Long id, Item item, Long quantity, Double totalWeight) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.totalWeight = totalWeight;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
