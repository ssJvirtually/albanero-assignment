package com.albanero.payment.controller;


import com.albanero.payment.entity.Payment;
import com.albanero.payment.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/paymentStatus/{orderId}")
    public Payment getPaymentDetailsByOrderId(@PathVariable ("orderId") Integer orderId){
     return paymentService.getPaymentDetailsByOrderId(orderId);
    }
}
