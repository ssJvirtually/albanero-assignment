package com.albanero.order.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue
    int id;
    int houseNo;
    String Street;
    String Town;
    String Country;
    @Column(nullable = false)
    int zipCode;
}
