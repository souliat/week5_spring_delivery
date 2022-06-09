package com.sparta.restaurant.dto;

import com.sparta.restaurant.model.Foods;
import com.sparta.restaurant.model.Order;
import com.sparta.restaurant.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    private Long deliveryFee;
    private Long totalPrice;
    private List<FoodsOrderResponseDto> foods;

    public OrderResponseDto(Restaurant restaurant) {
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.foods = new ArrayList<>();
    }

    public OrderResponseDto(Order order) {
        this.restaurantName = order.getRestaurant().getName();
        this.deliveryFee = order.getRestaurant().getDeliveryFee();
        this.foods = new ArrayList<>();
    }

}
