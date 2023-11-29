package com.albanero.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    int id;
    String userName;
    List<Integer> products;
    String paymentDetails;
    String deliveryAddress;
    String orderStatus;
    String eventType = "PAYMENT";
}
