package com.ebs.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.ebs.payment.client.BillService;
import com.ebs.payment.dto.PaymentDto;
import com.ebs.payment.excepion.PaymentNotFound;
import com.ebs.payment.model.Payment;
import com.ebs.payment.repository.PaymentRepository;
import com.ebs.payment.service.impl.PaymentServiceImpl;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BillService billService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPayBill_WhenPaymentExists_ThrowsException() {
       Long billId = 1L;
       Payment existingPayment = new Payment();
       when(paymentRepository.getByBillId(billId)).thenReturn(Optional.of(existingPayment));

       assertThrows(PaymentNotFound.class, () -> paymentService.payBill(billId));
    }

    @Test
    void testPayBill_WhenPaymentDoesNotExist_SavesPayment() {
       Long billId = 1L;
       when(paymentRepository.getByBillId(billId)).thenReturn(Optional.empty());

       paymentService.payBill(billId);

       verify(paymentRepository).save(any(Payment.class));
       verify(billService).updateBillStatus(billId);
    }

    @Test
    void testDeletePayment() {
       long paymentId = 1L;

       String response = paymentService.deletePayment(paymentId);

       verify(paymentRepository).deleteById(paymentId);
       assertEquals("Payment deleted successfully!!", response);
    }

    @Test
    void testGetPaymentById_WhenFound() {
       Long paymentId = 1L;
       Payment payment = new Payment();
       when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));
       when(modelMapper.map(payment, PaymentDto.class)).thenReturn(new PaymentDto());

       PaymentDto result = paymentService.getPaymentById(paymentId);

       assertNotNull(result);
    }

    @Test
    void testGetPaymentById_WhenNotFound_ThrowsException() {
       Long paymentId = 1L;
       when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

       assertThrows(PaymentNotFound.class, () -> paymentService.getPaymentById(paymentId));
    }

    @Test
    void testGetAllPayments() {
       List<Payment> payments = Arrays.asList(new Payment(), new Payment());
       when(paymentRepository.findAll()).thenReturn(payments);
       when(modelMapper.map(any(Payment.class), eq(PaymentDto.class))).thenReturn(new PaymentDto());

       List<PaymentDto> result = paymentService.getAllPayments();

       assertEquals(payments.size(), result.size());
    }

    @Test
    void testGetPaymentStatus_WhenPaymentExists() {
       Long billId = 1L;
       Payment payment = new Payment();
       when(paymentRepository.getByBillId(billId)).thenReturn(Optional.of(payment));

       String status = paymentService.getPaymentStatus(billId);

       assertEquals("PAID", status);
    }

    @Test
    void testGetPaymentStatus_WhenPaymentDoesNotExist() {
       Long billId = 1L;
       when(paymentRepository.getByBillId(billId)).thenReturn(Optional.empty());

       String status = paymentService.getPaymentStatus(billId);

       assertEquals("UNPAID", status);
    }
}