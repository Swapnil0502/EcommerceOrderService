package com.swapnil.Order.mapper;

import com.swapnil.Order.dto.OrderItemDto;
import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.entity.Order;
import com.swapnil.Order.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItem toEntity(Long productId, double price, OrderItemDto dto, double totalPrice, Order order){
        return OrderItem.builder()
                .order(order)
                .price(price)
                .totalPrice(totalPrice)
                .quantity(dto.getQuantity())
                .productId(dto.getProductId())
                .build();
    }
}
