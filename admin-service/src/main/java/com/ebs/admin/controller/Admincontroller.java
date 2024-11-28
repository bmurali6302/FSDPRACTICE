package com.ebs.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.admin.client.BillService;
import com.ebs.admin.client.CustomerService;
import com.ebs.admin.client.PaymentService;
import com.ebs.admin.dto.AdminDto;
import com.ebs.admin.dto.BillDto;
import com.ebs.admin.dto.CustomerDto;
import com.ebs.admin.dto.PaymentDto;
import com.ebs.admin.entity.Admin;
import com.ebs.admin.exceptions.InvalidEmailPasswordException;
import com.ebs.admin.service.AdminService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class Admincontroller implements CustomerService,BillService,PaymentService  {
	@Autowired
	AdminService adminService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/register")
    public ResponseEntity<String> adminRegister(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.adminLogin(admin.getEmailId(),admin.getPassword()));
    }
	@PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(AdminDto adminDto) {
        return ResponseEntity.ok(adminService.forgotPassword(adminDto));
    }
	
	
	///Customers
	
	@PostMapping("api/customer/register")
	public ResponseEntity<CustomerDto> registerCustomer( @RequestBody CustomerDto customer){
		try {
		return customerService.registerCustomer(customer);
		}catch(InvalidEmailPasswordException e) {
			throw new InvalidEmailPasswordException("Exception Thrown");
		}
	}
	

	@GetMapping("api/customer/view/{customerId}")
	public ResponseEntity<CustomerDto> viewCustomerByCustomerId(@PathVariable Long customerId){
		return customerService.viewCustomerByCustomerId(customerId);
	}
	
	@GetMapping("api/customer/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomer(){
		return customerService.getAllCustomer();
	}
	
	@DeleteMapping("api/customer/delete/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId){
		return customerService.deleteCustomer(customerId);
	}

	@PutMapping("api/customer/update/{customerId}")
	public ResponseEntity<String> editCustomer(@PathVariable Long customerId,@RequestBody CustomerDto customer){
		return customerService.editCustomer(customerId, customer);
	}

	 @PostMapping("api/bill/generate/{unitsConsumed}/{customerId}")
	    public ResponseEntity<BillDto> generateBill(@PathVariable double unitsConsumed,@PathVariable Long customerId) {
	        return billService.generateBill(unitsConsumed,customerId);
	    }
	 
	 @PutMapping("api/bill/update/{id}")
	    public ResponseEntity<BillDto> updateBill(@PathVariable Long id, @RequestBody BillDto billDto) {
	        return billService.updateBill(id, billDto);
	    }

	    @DeleteMapping("api/bill/delete/{id}")
	    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
	        billService.deleteBill(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PostMapping("api/payment/pay")
	    public ResponseEntity<String> generateBill(@RequestParam("billId")Long billId) {
	    	return paymentService.generateBill(billId);
	 
	    }
	    @GetMapping("api/payment/all")
	    public ResponseEntity<Iterable<PaymentDto>> getAllPayments() {
	        return paymentService.getAllPayments();
	    }
	    @DeleteMapping("api/payment/delete/{id}")
	    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
	       return  paymentService.deletePayment(id);
	        
	    }
	    @GetMapping("api/payment/{id}")
	    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
	        return paymentService.getPaymentById(id);
	    }

//		@Override
//		public ResponseEntity<String> generateBill() {
//			// TODO Auto-generated method stub
//			return null;
//		}
}