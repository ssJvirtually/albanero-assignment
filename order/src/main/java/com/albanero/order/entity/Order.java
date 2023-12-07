package com.albanero.order.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {


//    static {
//        ObjectMapper om = new ObjectMapper();
//        om.registerModule(new JavaTimeModule());
//    }

    @Id
    @GeneratedValue
    int id;
    String userName;
    List<Integer> products;
    String paymentDetails;
    String deliveryAddress;
    String orderStatus;
    String eventType = "ORDER";
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;
}
