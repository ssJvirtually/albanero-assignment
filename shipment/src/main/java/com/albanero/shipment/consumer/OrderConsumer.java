package com.albanero.shipment.consumer;



import com.albanero.shipment.entity.Order;
import com.albanero.shipment.service.ShipmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    ShipmentService shipmentService;


    @Autowired
    public OrderConsumer(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @KafkaListener(topics="${kafka.topic}",groupId = "${spring.kafka.consumer.group-id}")
    public void processOrders(String orderJson) {

        // Process the received order event
        System.out.println("Received order from Kafka: " + orderJson);

        log.info("order-event consumed :  {}",orderJson);

        ObjectMapper objectMapper = new ObjectMapper();


        Order order = null;
        try {
            order = objectMapper.readValue(orderJson, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(order.getEventType().equals("PAYMENT") && order.getOrderStatus().equals("PAYMENT_SUCCESS")){
            shipmentService.processShipment(order);
        }


    }

}
