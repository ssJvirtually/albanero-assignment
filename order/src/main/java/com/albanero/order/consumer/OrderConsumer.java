package com.albanero.order.consumer;


import com.albanero.order.entity.Order;
import com.albanero.order.entity.Payment;
import com.albanero.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {


    OrderService orderService;

    public  OrderConsumer(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(topics="${kafka.topic}",groupId = "${spring.kafka.consumer.group-id}")
    public void updateOrderStatus(String orderJson) {
        // Process the received order event
        System.out.println("Received order from Kafka: " + orderJson);

        ObjectMapper objectMapper = new ObjectMapper();

        Order order = null;
        try {
            order = objectMapper.readValue(orderJson, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(order != null) {
            // handle the data
            orderService.updateOrderStatus(order);
        }
    }
}
