package com.swapnil.Order.serviceImpl;

import com.swapnil.Order.clients.ProductServiceClient;
import com.swapnil.Order.dto.OrderItemDto;
import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;
import com.swapnil.Order.dto.ProductDto;
import com.swapnil.Order.entity.Order;
import com.swapnil.Order.entity.OrderItem;
import com.swapnil.Order.enums.OrderStatus;
import com.swapnil.Order.repository.OrderRepository;
import com.swapnil.Order.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductServiceClient productClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    private OrderItem orderItem;

    private OrderRequestDto req;

    private OrderItemDto dto;

    @BeforeEach
    void setUp(){

        orderItem = OrderItem.builder()
                .productId(1L)
                .price(299)
                .quantity(2)
                .totalPrice(598)
                .order(order)
                .build();
List<OrderItem> item = new ArrayList<>();
        order = Order.builder()
                .status(OrderStatus.PENDING)
                .items(item)
                .build();
        order.setId(1L);
item.add(orderItem);
        dto = OrderItemDto.builder()
                .productId(1L)
                .quantity(2)
                .build();
        List<OrderItemDto> items=new ArrayList<>();
        items.add(dto);
        req = OrderRequestDto.builder()
                .userId(1L)
                .items(items)
                .build();
    }

    @Test
    void createOrder_shouldReturnTheCreatedOrder(){

        ProductDto pdto = ProductDto.builder()
                .id(1L)
                .price(299)
                .brand("Fila")
                .image("image.png")
                .model("MH-001X")
                .title("shoes")
                .color("black")
                .build();
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(productClient.getProductbyId(1L)).thenReturn(pdto);

        OrderResponseDto result = orderService.createOrder(req);
        Order saved = orderRepository.save(order);
        assertEquals(result.getOrderId(),1L);
        assertEquals(result.getStatus(),OrderStatus.PENDING);

        verify(orderRepository, times(1)).save(order);
        verify(productClient, times(1)).getProductbyId(1L);
    }
}