package com.sparta.restaurant.service;

import com.sparta.restaurant.dto.FoodsOrderRequestDto;
import com.sparta.restaurant.dto.FoodsOrderResponseDto;
import com.sparta.restaurant.dto.OrderRequestDto;
import com.sparta.restaurant.dto.OrderResponseDto;
import com.sparta.restaurant.model.Foods;
import com.sparta.restaurant.model.Order;
import com.sparta.restaurant.model.Restaurant;
import com.sparta.restaurant.repository.FoodsRepository;
import com.sparta.restaurant.repository.OrderRepository;
import com.sparta.restaurant.repository.RestaurantRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodsRepository foodsRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(RestaurantRepository restaurantRepository, FoodsRepository foodsRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodsRepository = foodsRepository;
        this.orderRepository = orderRepository;
    }

    // 주문하기
    @Transactional
    public OrderResponseDto orderRegister(OrderRequestDto requestDto) { // {restaurantId, [foodsId, foodsQuantity]}가 담겨옴
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("해당하는 ID가 없습니다."));

        Order order = new Order(restaurant);
        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant);

        Long sum = orderResponseDto.getDeliveryFee();

        for (FoodsOrderRequestDto foodsOrderRequestDto : requestDto.getFoods()) {
            if(foodsOrderRequestDto.getQuantity() < 1 || foodsOrderRequestDto.getQuantity() > 100) {
                throw new IllegalArgumentException("허용 범위 초과!");
            }

            order.getFoodsMap().put(foodsOrderRequestDto.getId(), foodsOrderRequestDto.getQuantity());

            Foods food = foodsRepository.findById(foodsOrderRequestDto.getId()).orElseThrow(
                    () -> new NullPointerException("해당 아이디가 없습니다."));

            order.getFoodsList().add(food);

            FoodsOrderResponseDto responseDto = new FoodsOrderResponseDto(
                    food.getName(),
                    foodsOrderRequestDto.getQuantity(),
                    foodsOrderRequestDto.getQuantity() * food.getPrice()
            );

            orderResponseDto.getFoods().add(responseDto);

            sum += responseDto.getPrice();
        }
        if(sum - orderResponseDto.getDeliveryFee() < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("주문 음식 가격의 총합은 최소 주문가격을 넘어야합니다.");
        }
        orderRepository.save(order);
        orderResponseDto.setTotalPrice(sum);

        return orderResponseDto;
    }

    // 전체 주문내역 조회하기
    public List<OrderResponseDto> getTotalOrders() {

        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList= new ArrayList<>();

        for (Order order : orderList) {
            OrderResponseDto orderResponseDto = new OrderResponseDto(order);
            Long sum = orderResponseDto.getDeliveryFee();
            List<FoodsOrderResponseDto> foodsOrderResponseDtoList = new ArrayList<>();

            for(Foods food : order.getFoodsList()) {
                Long quantity = order.getFoodsMap().get(food.getId());
                FoodsOrderResponseDto foodsOrderResponseDto = new FoodsOrderResponseDto(
                        food.getName(), quantity, food.getPrice() * quantity);

                foodsOrderResponseDtoList.add(foodsOrderResponseDto);
                sum += foodsOrderResponseDto.getPrice();
            }
            orderResponseDto.setTotalPrice(sum);
            orderResponseDto.getFoods().addAll(foodsOrderResponseDtoList);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;

    }
}
