package com.albanero.payment.service;

import com.albanero.payment.entity.Order;
import com.albanero.payment.entity.Payment;
import com.albanero.payment.producer.OrderProducer;
import com.albanero.payment.producer.PaymentProducer;
import com.albanero.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class PaymentService {
    private final static String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    private final static String PAYMENT_FAILED = "PAYMENT_FAILED";


    PaymentProducer paymentProducer;

    OrderProducer orderProducer;
    PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentProducer paymentProducer,PaymentRepository paymentRepository,OrderProducer orderProducer){

        this.paymentProducer = paymentProducer;
        this.paymentRepository = paymentRepository;
        this.orderProducer = orderProducer;
    }

    public void ProcessPayment(Order order){
        boolean paymentStatus = dummyPaymentHandler(order.getPaymentDetails());
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPaymentStatus(paymentStatus);
        savePayment(payment);

        if(paymentStatus){
            order.setOrderStatus(PAYMENT_SUCCESS);
        }
        else{
            order.setOrderStatus(PAYMENT_FAILED);
        }

        //paymentProducer.publishPaymentStatusForOrder(payment);
        orderProducer.publishOrder(order);
    }

    public Payment savePayment(Payment payment){
      return  paymentRepository.save(payment);
    }


    public boolean dummyPaymentHandler(String payload){
        boolean status = new Random().nextBoolean();
        return status;
    }

    public Payment getPaymentDetailsByOrderId(Integer orderId) {
      return   paymentRepository.findByOrderId(orderId);
    }
}
