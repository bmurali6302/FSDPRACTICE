package com.ebs.payment.excepion;

public class PaymentNotFound extends RuntimeException{
    public PaymentNotFound(String message) {
        super(message);
    }
}
