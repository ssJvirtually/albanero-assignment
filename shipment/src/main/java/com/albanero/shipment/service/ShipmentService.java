package com.albanero.shipment.service;


import com.albanero.shipment.entity.Order;
import com.albanero.shipment.entity.Shipment;
import com.albanero.shipment.producer.OrderProducer;
import com.albanero.shipment.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {



    private final static String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    private final static String PAYMENT_FAILED = "PAYMENT_FAILED";

    private final static String SHIPMENT_STARTED = "SHIPMENT_STARTED";

    private final static String SHIPMENT_HOLD = "SHIPMENT_HOLD";



    OrderProducer orderProducer;

    ShipmentRepository shipmentRepository;

    public ShipmentService(OrderProducer orderProducer,ShipmentRepository shipmentRepository){
        this.orderProducer = orderProducer;
        this.shipmentRepository = shipmentRepository;
    }


    public void processShipment(Order order) {
        Shipment shipment = new Shipment();
        shipment.setOrderId(order.getId());

        if(order.getOrderStatus().equals(PAYMENT_SUCCESS)){
            order.setOrderStatus(SHIPMENT_STARTED);
            shipment.setShipmentStatus(true);
        }
        else{
            shipment.setShipmentStatus(false);
            order.setOrderStatus(SHIPMENT_HOLD);
        }
        orderProducer.publishOrder(order);
        save(shipment);
    }

    public Shipment save(Shipment shipment){
      return  shipmentRepository.save(shipment);
    }


    public Shipment getShipmentDetailsByOrderId(Integer orderId) {
        return shipmentRepository.findByOrderId(orderId);
    }
}
