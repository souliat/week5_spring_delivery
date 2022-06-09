package com.sparta.restaurant.controller;

import com.sparta.restaurant.dto.RestaurantRequestDto;
import com.sparta.restaurant.model.Restaurant;
import com.sparta.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant register(@RequestBody RestaurantRequestDto requestDto) {
       return restaurantService.register(requestDto);
    }

    // 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getList() {

        return restaurantService.getList();
    }
}
