package com.ebs.payment.service;

import java.util.List;

import com.ebs.payment.dto.PaymentDto;
import com.ebs.payment.model.Payment;

public interface PaymentService {
    public Payment payBill(Long billId);
    public String deletePayment(long id);
    public PaymentDto getPaymentById(Long paymentId);
    public List<PaymentDto> getAllPayments();
    
    
    
    
    String getPaymentStatus(Long billId);
}
