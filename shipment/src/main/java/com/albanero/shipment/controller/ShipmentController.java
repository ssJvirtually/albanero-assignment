package com.albanero.shipment.controller;

import com.albanero.shipment.entity.Shipment;
import com.albanero.shipment.service.ShipmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShipmentController {


    ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @GetMapping("/shipment/{orderId}")
    public Shipment getPaymentDetailsByOrderId(@PathVariable Integer orderId){
        return shipmentService.getShipmentDetailsByOrderId(orderId);
    }
}
