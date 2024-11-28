package com.ebs.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebs.payment.dto.BillDto;

@FeignClient(name="billing-service",url="http://localhost:8092")
public interface BillService {
	
	@GetMapping("api/bill/{id}")
    public ResponseEntity<BillDto> getBill(@PathVariable Long id); 
	
	 @PutMapping("api/bill/update/{id}")
	  public ResponseEntity<BillDto> updateBill(@PathVariable Long id, @RequestBody BillDto billDto);
	 
	 @PutMapping("api/bill/updatebillstatus/{billId}")
	    public ResponseEntity<String> updateBillStatus(@PathVariable Long billId) ;

}