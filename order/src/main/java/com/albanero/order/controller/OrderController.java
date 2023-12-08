package com.albanero.order.controller;


import com.albanero.order.config.ExceptionHandlerConfig;
import com.albanero.order.entity.Order;
import com.albanero.order.producer.OrderProducer;
import com.albanero.order.service.OrderService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import java.util.Optional;

@RestController
@Slf4j
public class OrderController {


    OrderService orderService;

    OrderProducer orderProducer;


    @Autowired
    public OrderController(OrderService orderService,OrderProducer orderProducer){
        this.orderService = orderService;
        this.orderProducer = orderProducer;
    }


    @GetMapping("/order")
    public Order createOrder(@RequestBody Order order){
       order =  orderService.saveOrder(order);
       orderService.publishOrderAsync(order);
       return order;

    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOrder(@PathVariable  Integer id)
    {
       return orderService.getOrder(id);
    }
}
