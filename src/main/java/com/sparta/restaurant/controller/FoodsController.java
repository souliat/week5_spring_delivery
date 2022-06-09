package com.sparta.restaurant.controller;

import com.sparta.restaurant.dto.FoodsDto;
import com.sparta.restaurant.model.Foods;
import com.sparta.restaurant.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodsController {

    private final FoodsService foodsService;

    @Autowired
    public FoodsController(FoodsService foodsService) {

        this.foodsService = foodsService;
    }

    // 레스토랑 음식 등록하기
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFoods(@RequestBody List<FoodsDto> requestDtoList, @PathVariable Long restaurantId) {
        foodsService.registerFoods(requestDtoList, restaurantId);
    }

    // 등록 음식 조회
    @GetMapping("restaurant/{restaurantId}/foods")
    public List<Foods> getMenus(@PathVariable Long restaurantId) {
        return foodsService.getMenus(restaurantId);
    }
}
