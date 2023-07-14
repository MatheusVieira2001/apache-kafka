package com.matheus.paymentservice.services;

import com.matheus.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
