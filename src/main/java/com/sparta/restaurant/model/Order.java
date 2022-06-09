package com.sparta.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order {

    // ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ElementCollection
    private Map<Long, Long> foodsMap;

    @ManyToMany
    @JoinTable(name="ORDER_FOODS", joinColumns = @JoinColumn(name="ORDER_ID"), inverseJoinColumns = @JoinColumn(name="FOOD_ID"))
    private List<Foods> foodsList = new ArrayList<>();

    @ManyToOne
    private Restaurant restaurant;

    public Order (Restaurant restaurant) {
        this.restaurant = restaurant;
        this.foodsMap = new HashMap<>();
    }



}
