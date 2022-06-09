package com.sparta.restaurant.validator;

import com.sparta.restaurant.dto.FoodsDto;
import com.sparta.restaurant.dto.RestaurantRequestDto;
import com.sparta.restaurant.model.Foods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {
    public static void validateProductInput(RestaurantRequestDto requestDto) {

        Long price = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();

        if (price < 1000 || price > 100000) {
            throw new IllegalArgumentException("주문 가격이 허용범위를 초과했습니다.");
        }

        if (price % 100 != 0) {
            throw new IllegalArgumentException("주문 가격이 100원 단위로 입력이 되지 않았습니다.");
        }

        if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("배달비가 허용범위를 초과했습니다.");
        }

        if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("배달 금액이 500원 단위로 입력이 되지 않았습니다.");
        }

    }

    public static void foodNameValidator(List<Foods> existList, FoodsDto requestDto) {
        for (Foods list : existList) {
            if(list.getName().equals(requestDto.getName())) {
                throw new IllegalArgumentException("중복된 음식명 입니다. " + requestDto.getName());
            }
        }
    }

    public static void foodPriceValidator(Long price) {

        if (price < 100 || price > 1000000) {
            throw new IllegalArgumentException("주문 가격이 허용범위를 초과했습니다.");
        }
        if (price % 100 != 0) {
            throw new IllegalArgumentException("주문 가격이 100원 단위로 입력이 되지 않았습니다.");
        }
    }
}
