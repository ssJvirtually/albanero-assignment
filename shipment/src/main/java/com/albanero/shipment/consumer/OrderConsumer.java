package com.albanero.shipment.consumer;



import com.albanero.shipment.entity.Order;
import com.albanero.shipment.service.ShipmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    ShipmentService shipmentService;


    @Autowired
    public OrderConsumer(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @KafkaListener(topics="${kafka.topic}",groupId = "${spring.kafka.consumer.group-id}")
    public void processOrders(String orderJson) {


        if(!orderJson.contains("PAYMENT")){
            return;
        }
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
            shipmentService.processShipment(order);
        }
    }

}
