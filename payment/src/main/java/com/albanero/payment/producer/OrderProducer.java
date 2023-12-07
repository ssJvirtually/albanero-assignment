package com.albanero.payment.producer;

import com.albanero.payment.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class OrderProducer {

    @Value("${kafka.topic}")
    String topic;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String kafkaBootstrapServers;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    public void publishOrder(Order order){

            order.setEventType("PAYMENT");
            // Convert the Order object to a JSON string (you may use a library like Jackson or Gson)
            String orderJson = convertOrderToJson(order);
            // Send the order to the Kafka topic
            kafkaTemplate.send(new ProducerRecord<>(topic, orderJson));

        try {
            logger.info("payment event published : {}" , new ObjectMapper().writeValueAsString( order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }


    private String convertOrderToJson(Order order) {
         ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
