package com.albanero.shipment.repository;

import com.albanero.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
    Shipment findByOrderId(Integer orderId);
}
