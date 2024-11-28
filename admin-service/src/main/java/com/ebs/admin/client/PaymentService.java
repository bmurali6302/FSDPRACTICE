package com.ebs.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebs.admin.dto.PaymentDto;

@FeignClient(name="payment-service",url="http://localhost:8094")
public interface PaymentService {
	
	
	    @PostMapping("api/payment/pay")
	    public ResponseEntity<String> generateBill(@RequestParam("billId") Long billId);
	    
	    @GetMapping("api/payment/all")
	    public ResponseEntity<Iterable<PaymentDto>> getAllPayments(); 
	    
	    @DeleteMapping("api/payment/delete/{id}")
	    public ResponseEntity<Void> deletePayment(@PathVariable Long id);  
	    
	    @GetMapping("api/payment/{id}")
	    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id);   

}
