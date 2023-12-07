package com.albanero.payment.producer;


import com.albanero.payment.entity.Order;
import com.albanero.payment.entity.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class PaymentProducer {

    @Value("${kafka.topic}")
    String topic;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String kafkaBootstrapServers;


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void publishPaymentStatusForOrder(Payment payment) {

        // Convert the payment object to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(payment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Send the order to the Kafka topic
            kafkaTemplate.send(topic,orderJson);
    }



        public void publishOrderStatus(Order order){

            // Convert the Order object to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = null;
            try {
                orderJson = objectMapper.writeValueAsString(order);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            // Send the order to the Kafka topic
            kafkaTemplate.send(topic,orderJson);
    }



}
