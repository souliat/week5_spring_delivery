package com.sparta.restaurant.service;

import com.sparta.restaurant.dto.FoodsDto;
import com.sparta.restaurant.model.Foods;
import com.sparta.restaurant.model.Menu;
import com.sparta.restaurant.model.Restaurant;
import com.sparta.restaurant.repository.FoodsRepository;
import com.sparta.restaurant.repository.MenuRepository;
import com.sparta.restaurant.repository.RestaurantRepository;
import com.sparta.restaurant.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodsService {
    private final FoodsRepository foodsRepository;
    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodsService(FoodsRepository foodsRepository, MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.foodsRepository = foodsRepository;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 레스토랑 음식 등록
    @Transactional
    public void registerFoods(List<FoodsDto> requestDtoList, Long restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        Restaurant restaurant = restaurantRepository.findByMenuId(menu.getId());

        List<Foods> foodsList = new ArrayList<>();
        for (FoodsDto requestDto : requestDtoList) {

            if(menu.getFoodsList().size() != 0) {
                List<Foods> existList = menu.getFoodsList();
                Validator.foodNameValidator(existList, requestDto);
            }

            Validator.foodPriceValidator(requestDto.getPrice());

            Foods food = new Foods(requestDto);
//            menu.getFoodsList().add(food);
            food.setMenu(menu);
            food.setRestaurant(restaurant);

            foodsRepository.save(food);
            foodsList.add(food);
        }

    }

    // 등록 음식 조회
    public List<Foods> getMenus(Long restaurantId) {

        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        return menu.getFoodsList();
    }
}
