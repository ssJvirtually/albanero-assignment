package com.albanero.order.controller;


import com.albanero.order.entity.Order;
import com.albanero.order.producer.OrderProducer;
import com.albanero.order.service.OrderService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
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

        Order order1 = orderService.saveOrder(order);
        orderProducer.publishOrder(order1);
        return order1;
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOrder(@PathVariable  String id){
       return orderService.getOrder(Integer.parseInt(id));
    }
}
