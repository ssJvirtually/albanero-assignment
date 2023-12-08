package com.albanero.order.service;

import com.albanero.order.entity.Order;
import com.albanero.order.producer.OrderProducer;
import com.albanero.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class OrderService {


    OrderRepository orderRepository;

    OrderProducer orderProducer;

    public final static String ORDER_CREATED = "ORDER_CREATED";


    public OrderService(OrderRepository orderRepository,OrderProducer orderProducer){
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public Order saveOrder(Order order) {
       order.setOrderStatus(ORDER_CREATED);
       return orderRepository.save(order);
    }
    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Async
    public void publishOrderAsync(Order order) {
        try {
            orderProducer.publishOrder(order);
        } catch (KafkaException kafkaException) {
            log.error("kakfa error log :: "+kafkaException.getMessage());
            // You can handle the exception here or log it as needed
        }
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
