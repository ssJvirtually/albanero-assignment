package com.albanero.order.producer;

import com.albanero.order.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class OrderProducer {

    @Value("${kafka.topic}")
    String topic;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String kafkaBootstrapServers;


    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);


    public void publishOrder(Order order){

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaBootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
            // Convert the Order object to a JSON string (you may use a library like Jackson or Gson)
            String orderJson = convertOrderToJson(order);
            // Send the order to the Kafka topic
            producer.send(new ProducerRecord<>(topic, orderJson));

            logger.info("order event published : {}" , new ObjectMapper().writeValueAsString( order));
        } catch (Exception e) {
            e.printStackTrace();
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
