package com.swapnil.Order.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long userId;

    private List<OrderItemDto> items;
}
