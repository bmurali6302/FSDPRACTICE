package com.ebs.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebs.admin.dto.BillDto;

@FeignClient(name="billing-service",url="http://localhost:8092")
public interface BillService {
	
	
	 @PostMapping("api/bill/generate/{unitsConsumed}/{customerId}")
	    public ResponseEntity<BillDto> generateBill(@PathVariable double unitsConsumed,@PathVariable Long customerId);
	 
	 
	 
	 @PutMapping("api/bill/update/{id}")
	    public ResponseEntity<BillDto> updateBill(@PathVariable Long id, @RequestBody BillDto billDto) ;
	       

	    @DeleteMapping("api/bill/delete/{id}")
	    public ResponseEntity<Void> deleteBill(@PathVariable Long id); 
	       
	
}
