package com.sparta.restaurant.repository;

import com.sparta.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByMenuId(Long menuId);

}
