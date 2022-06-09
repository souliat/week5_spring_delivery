package com.sparta.restaurant.repository;

import com.sparta.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByRestaurantId(Long restaurantId);
}
