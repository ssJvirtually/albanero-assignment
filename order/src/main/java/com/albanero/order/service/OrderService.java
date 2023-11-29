package com.albanero.order.service;

import com.albanero.order.entity.Order;
import com.albanero.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {


    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
       return orderRepository.save(order);
    }


    public void updateOrderStatus(Order order) {
        Optional<Order> orderById = orderRepository.findById(order.getId());
        if(orderById.isPresent()){
            Order order1 = orderById.get();
            order1.setOrderStatus(order.getOrderStatus());
            orderRepository.save(order1);
        }
    }
}
