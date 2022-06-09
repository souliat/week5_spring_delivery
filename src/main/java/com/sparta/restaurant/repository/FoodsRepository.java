package com.sparta.restaurant.repository;

import com.sparta.restaurant.model.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodsRepository extends JpaRepository<Foods, Long> {
    List<Foods> findAllByRestaurantId(Long restaurantId);
}
