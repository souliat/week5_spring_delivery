package com.sparta.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Menu {
    // ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // Menu와 Restaurant 1:1의 연관관계
    @OneToOne
    @JoinColumn(name ="RESTAURANT_ID")
    private Restaurant restaurant;

    // Menu와 Foods는 1:N의 연관관계
    @JsonBackReference
    @OneToMany(mappedBy = "menu")
    private List<Foods> foodsList = new ArrayList<>();

    public Menu(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
