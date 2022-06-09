package com.sparta.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodsOrderRequestDto> foods;
}
