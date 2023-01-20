package net.yorksolutions.peternepomucenopantrybe.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @OneToOne// (cascade = CascadeType.DETACH)
//    private Item item;


    //private Long itemNo;


    private String name;

    private String measurement;
    private Double quantity;
    private Double totalWeight;

    private Double totalCalories;

    public Ingredient(Long id, Long itemNo, String name, String measurement, Double quantity, Double totalWeight, Double totalCalories) {
        this.id = id;
        ///this.itemNo = itemNo;
        this.name = name;
        this.measurement = measurement;
        this.quantity = quantity;
        this.totalWeight = totalWeight;
        this.totalCalories = totalCalories;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


//    public Long getItemNo() {
//        return itemNo;
//    }

//    public void setItemNo(Long itemNo) {
//        this.itemNo = itemNo;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
