package com.albanero.shipment.controller;

import com.albanero.shipment.entity.Shipment;
import com.albanero.shipment.service.ShipmentService;
import org.springframework.web.bind.annotation.GetMapping;

public class ShipmentController {


    ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @GetMapping("/orderId")
    public Shipment getPaymentDetailsByOrderId(Integer orderId){
        return shipmentService.getShipmentDetailsByOrderId(orderId);
    }
}
