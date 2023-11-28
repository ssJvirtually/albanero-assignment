package com.albanero.payment.consumer;


import com.albanero.payment.entity.Order;
import com.albanero.payment.entity.Payment;
import com.albanero.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class OrderConsumer {

    PaymentService paymentService;


    @Autowired
    public OrderConsumer(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @KafkaListener(topics="${kafka.topic}",groupId = "${spring.kafka.consumer.group-id}")
    public void processOrders(String orderJson) {
        // Process the received order event
        System.out.println("Received order from Kafka: " + orderJson);

        ObjectMapper objectMapper = new ObjectMapper();

        if(orderJson.contains("PAYMENT")){
            return;
        }

        Order order = null;
        try {
            order = objectMapper.readValue(orderJson, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(order != null) {
            // handle the data
            paymentService.ProcessPayment(order);
        }
    }

}
