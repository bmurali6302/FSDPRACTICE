package com.ebs.billing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebs.billing.dto.CustomerDto;
@FeignClient(name="CustomerService",url="http://localhost:8091")
public interface CustomerService {
	@GetMapping("/api/customer/view/{customerId}")
	public ResponseEntity<CustomerDto> viewCustomerByCustomerId(@PathVariable Long customerId);

}

