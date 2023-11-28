package com.albanero.payment.controller;


import com.albanero.payment.entity.Payment;
import com.albanero.payment.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/orderId")
    public Payment getPaymentDetailsByOrderId(Integer orderId){
     return paymentService.getPaymentDetailsByOrderId(orderId);
    }
}
