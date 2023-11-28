package com.albanero.order.service;

import com.albanero.order.entity.Order;
import com.albanero.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
       return orderRepository.save(order);
    }
}
