package com.sparta.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodsOrderResponseDto {
    private String name;
    private Long quantity;
    private Long price;

    public FoodsOrderResponseDto(String name, Long quantity, Long price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
