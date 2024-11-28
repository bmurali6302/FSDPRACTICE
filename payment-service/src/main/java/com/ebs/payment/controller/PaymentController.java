package com.ebs.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.payment.client.BillService;
import com.ebs.payment.dto.BillDto;
import com.ebs.payment.dto.PaymentDto;
import com.ebs.payment.model.Payment;
import com.ebs.payment.service.PaymentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController implements BillService{
	
	@Autowired
	BillService billservice;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay/{billId}")
    public ResponseEntity<Payment> generateBill(@PathVariable Long billId) {
        return ResponseEntity.ok(paymentService.payBill(billId));
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }
    @GetMapping("api/bill/{id}")
    public ResponseEntity<BillDto> getBill(@PathVariable Long id) {
        return billservice.getBill(id);
    }
    
    @GetMapping("/status/{billId}")
    public ResponseEntity<String> getPaymentStatus(@PathVariable Long billId) {
    	if (paymentService.getPaymentStatus(billId) == null) {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.ok(paymentService.getPaymentStatus(billId));
    }
    
    
    @PutMapping("api/bill/update/{id}")
    public ResponseEntity<BillDto> updateBill(@PathVariable Long id, @RequestBody BillDto billDto) {
        return billservice.updateBill(id, billDto);
    }
    @PutMapping("api/bill/updatebillstatus/{billId}")
    public ResponseEntity<String> updateBillStatus(@PathVariable Long billId) {
        return billservice.updateBillStatus(billId);
    }
}
