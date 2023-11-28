package com.albanero.payment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_table")
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int orderId;
    boolean paymentStatus;
    String eventType = "PAYMENT";

}
