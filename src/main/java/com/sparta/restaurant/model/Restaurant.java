package com.sparta.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.restaurant.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Restaurant extends Timestamped {

    // ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 음식점 이름
    @Column(nullable = false)
    private String name;

    // 최소 주문 가격
    @Column(nullable = false)
    private Long minOrderPrice;

    // 기본 배달비
    @Column(nullable = false)
    private Long deliveryFee;

    @JsonBackReference
    @OneToMany(mappedBy = "restaurant")
    private List<Foods> foodsList = new ArrayList<>();

    //Restaurant과 Menu는 1:1의 연관관계
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;


    public Restaurant(RestaurantRequestDto requestDto) {

        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


}
