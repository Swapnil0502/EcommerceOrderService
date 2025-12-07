package com.swapnil.Order.mapper;

import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;
import com.swapnil.Order.entity.Order;
import com.swapnil.Order.enums.OrderStatus;

public class OrderMapper {

    public static Order toEntity(OrderRequestDto dto){
        return Order.builder()
                .userId(dto.getUserId())
                .status(OrderStatus.PENDING)
                .build();
    }

    public static OrderResponseDto toOrderResponseDto(Order order){
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }
}
