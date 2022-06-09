package com.sparta.restaurant.controller;

import com.sparta.restaurant.dto.OrderRequestDto;
import com.sparta.restaurant.dto.OrderResponseDto;
import com.sparta.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto orderRegister(@RequestBody OrderRequestDto requestDto) {

        return orderService.orderRegister(requestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> totalOrders() {

        return orderService.getTotalOrders();
    }

}
