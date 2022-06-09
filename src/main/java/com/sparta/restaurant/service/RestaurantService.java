package com.sparta.restaurant.service;

import com.sparta.restaurant.dto.RestaurantRequestDto;
import com.sparta.restaurant.model.Menu;
import com.sparta.restaurant.model.Restaurant;
import com.sparta.restaurant.repository.MenuRepository;
import com.sparta.restaurant.repository.RestaurantRepository;
import com.sparta.restaurant.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    // 음식점 등록하기
    @Transactional
    public Restaurant register(RestaurantRequestDto requestDto){

        Validator.validateProductInput(requestDto);

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);

        Menu menu = new Menu(restaurant);
        restaurant.setMenu(menu);
        menuRepository.save(menu);

        return restaurant;
    }

    // 음식점 조회하기
    public List<Restaurant> getList() {
        return restaurantRepository.findAll();
    }
}
