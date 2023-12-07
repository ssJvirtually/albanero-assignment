package com.albanero.order.config;


import com.albanero.order.entity.Order;
import com.albanero.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class HealthCheck {

    private final OrderService orderService;


    @Autowired
    public HealthCheck(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 60000) // Run every minute (in milliseconds)
    public void runHealthCheck() {
        System.out.println("Running health check...");

        // Get the last 5 orders
        List<Order> lastFiveOrders = orderService.getLastFiveOrders();

        log.info("last five orders : {}",lastFiveOrders);
        //System.out.println(lastFiveOrders);

        // Check order status and raise alert if ORDER_CREATED
        if(lastFiveOrders.stream().filter(order -> order.getOrderStatus().equals("ORDER_CREATED")).count() >= 5){
                log.info("Payment service is  down");
                // Send notification logic can be added here
        }


        if(lastFiveOrders.stream().filter(order -> order.getOrderStatus().equals("PAYMENT_SUCCESS")).count() >= 5){
                log.info("Shipment service is down");
                // Send notification logic can be added here
        }
        }
}

