package com.swapnil.Order.service;

import com.swapnil.Order.clients.ProductServiceClient;
import com.swapnil.Order.dto.OrderItemDto;
import com.swapnil.Order.dto.OrderRequestDto;
import com.swapnil.Order.dto.OrderResponseDto;
import com.swapnil.Order.dto.ProductDto;
import com.swapnil.Order.entity.Order;
import com.swapnil.Order.entity.OrderItem;
import com.swapnil.Order.enums.OrderStatus;
import com.swapnil.Order.mapper.OrderItemMapper;
import com.swapnil.Order.mapper.OrderMapper;
import com.swapnil.Order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productClient;

    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceClient productClient){
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Override
    public OrderResponseDto createProduct(OrderRequestDto req) {

        Order order = OrderMapper.toEntity(req);


        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto itemDto : req.getItems()) {
            ProductDto product = productClient.getProductbyId(itemDto.getProductId());
            double price = product.getPrice();
            double totalPrice = itemDto.getQuantity() * price;
            OrderItem orderItem = OrderItemMapper.toEntity(product.getId(), price, itemDto, totalPrice, order);
            items.add(orderItem);

        }
        order.setItems(items);

        Order createdOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponseDto(createdOrder);
    }
}
