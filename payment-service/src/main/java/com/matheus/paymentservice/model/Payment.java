package com.matheus.paymentservice.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Payment implements Serializable {

    private Long id;
    private Long idUser;
    private Long idProduct;
    private String cardNumber;

    @Override
    public String toString() {
        return "Payment{" +
                "idUser=" + idUser +
                ", idProduct=" + idProduct +
                '}';
    }
}
