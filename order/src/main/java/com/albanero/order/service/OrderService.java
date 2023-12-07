package com.albanero.order.service;

import com.albanero.order.entity.Order;
import com.albanero.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    OrderRepository orderRepository;

    public final static String ORDER_CREATED = "ORDER_CREATED";


    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
       order.setOrderStatus(ORDER_CREATED);
       return orderRepository.save(order);
    }
    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getLastFiveOrders() {
        return orderRepository.findLastFiveOrders();
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
