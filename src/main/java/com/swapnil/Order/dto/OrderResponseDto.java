package com.swapnil.Order.dto;

import com.swapnil.Order.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private OrderStatus status;
}
