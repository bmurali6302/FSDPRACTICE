package com.ebs.admin.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebs.admin.dto.CustomerDto;
@FeignClient(name="CustomerService",url="http://localhost:8091")
public interface CustomerService {

	@GetMapping("api/customer/view/{customerId}")
	public ResponseEntity<CustomerDto> viewCustomerByCustomerId(@PathVariable Long customerId);
		
	@PostMapping("api/customer/register")
	public ResponseEntity<CustomerDto> registerCustomer( @RequestBody CustomerDto customer);
	
	
	@GetMapping("api/customer/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomer();

	
	@PutMapping("api/customer/update/{customerId}")
	public ResponseEntity<String> editCustomer(@PathVariable Long customerId,@RequestBody CustomerDto customer);
		
	
	@DeleteMapping("api/customer/delete/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId);
		
		
	}

	//http://localhost:8091/api/customer/register

