package com.albanero.payment.producer;


import com.albanero.payment.entity.Order;
import com.albanero.payment.entity.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class PaymentProducer {

    @Value("${kafka.topic}")
    String topic;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String kafkaBootstrapServers;



    public void publishPaymentStatusForOrder(Payment payment) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaBootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
            // Convert the payment object to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(payment);
            // Send the order to the Kafka topic
            producer.send(new ProducerRecord<>(topic, orderJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        public void publishOrderStatus(Order order){
            Properties properties = new Properties();
            properties.put("bootstrap.servers", kafkaBootstrapServers);
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
                // Convert the Order object to a JSON string
                ObjectMapper objectMapper = new ObjectMapper();
                String orderJson = objectMapper.writeValueAsString(order);
                // Send the order to the Kafka topic
                producer.send(new ProducerRecord<>(topic, orderJson));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }



}
