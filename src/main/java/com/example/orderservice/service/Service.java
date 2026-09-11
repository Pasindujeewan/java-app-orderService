package com.example.orderservice.service;


import com.example.orderservice.dto.OrderLineDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);
    }


    private OrderLineItems mapToDto(OrderLineDto orderLineDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineDto.getPrice());
        orderLineItems.setQuantity(orderLineDto.getQuantity());
        orderLineItems.setSkuCode(orderLineDto.getSkuCode());

        return orderLineItems;
    }
}
