package com.ebs.payment.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.payment.client.BillService;
import com.ebs.payment.dto.PaymentDto;
import com.ebs.payment.excepion.PaymentNotFound;
import com.ebs.payment.model.Payment;
import com.ebs.payment.repository.PaymentRepository;
import com.ebs.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    BillService billservice;

    @Override
    public Payment payBill(Long billId) {
        Payment payment = new Payment();
        payment.setBillId(billId);
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus("PAID");
        Optional<Payment> existingPayment = paymentRepository.getByBillId(billId);
        if(existingPayment.isPresent()) {
        	throw new PaymentNotFound("Payment Data already Exists with the given Bill Id");
        }
        billservice.updateBillStatus(billId);
        return paymentRepository.save(payment);
    }


    @Override
    public String deletePayment(long id) {
        paymentRepository.deleteById(id);
        return "Payment deleted successfully!!";
    }

    @Override
    public PaymentDto getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
                () -> new PaymentNotFound("Payment not found")
        );
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public String getPaymentStatus(Long billId) {
        Optional<Payment> paymentOptional = paymentRepository.getByBillId(billId);
        if (paymentOptional.isPresent()) {
        	System.out.println(paymentOptional.get());
            return "PAID";
        } else {
            return "UNPAID";
        }
    }

}
