package com.swapnil.Order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.Order.dto.OrderItemDto;
import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;
import com.swapnil.Order.enums.OrderStatus;
import com.swapnil.Order.exception.GlobalExceptionHandler;
import com.swapnil.Order.service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import jakarta.ws.rs.core.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private IOrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createOrder_shouldCreateAndReturnCreatedOrder() throws Exception{

        OrderResponseDto dto = OrderResponseDto.builder()
                .orderId(1L)
                .status(OrderStatus.PENDING)
                .build();

        OrderItemDto itemDto = OrderItemDto.builder()
                .productId(1L)
                .quantity(2)
                .build();
        List<OrderItemDto> items=new ArrayList<>();
        items.add(itemDto);
        OrderRequestDto req = OrderRequestDto.builder()
                .userId(1L)
                .items(items)
                .build();
        when(orderService.createOrder(any())).thenReturn(dto);
        String json = objectMapper.writeValueAsString(req);
        mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath( "$.orderId").value(1L))
                .andExpect(jsonPath( "$.status").value(OrderStatus.PENDING.name()));

    }
}
