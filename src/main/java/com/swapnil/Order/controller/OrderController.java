package com.swapnil.Order.controller;

import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;
import com.swapnil.Order.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService)
    {
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto req){
        OrderResponseDto data = orderService.createOrder(req);
        return ResponseEntity.ok(data);
    }
}
