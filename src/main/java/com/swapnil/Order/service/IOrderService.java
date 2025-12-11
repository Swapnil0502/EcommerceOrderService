package com.swapnil.Order.service;

import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;

public interface IOrderService {

    OrderResponseDto createOrder(OrderRequestDto req);
}
