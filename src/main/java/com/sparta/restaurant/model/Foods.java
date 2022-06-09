package com.sparta.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.restaurant.dto.FoodsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Foods {

    // ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 음식명
    @Column(nullable = false)
    private String name;

    // 음식 가격
    @Column(nullable = false)
    private Long price;

    // Foods와 Restaurant는 N:1의 연관관계
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    // Foods와 Menu는 N:1의 연관관계
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;


    public Foods(FoodsDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getFoodsList().add(this);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.getFoodsList().add(this);
    }

}
