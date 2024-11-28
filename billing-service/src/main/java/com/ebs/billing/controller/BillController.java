package com.ebs.billing.controller;
import java.util.List;

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

import com.ebs.billing.dto.BillDto;
import com.ebs.billing.model.Bill;
import com.ebs.billing.service.BillService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;
    
    @GetMapping("/{id}")
    public ResponseEntity<BillDto> getBill(@PathVariable Long id) {
        BillDto bill = billService.getBill(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BillDto>> getAllBills() {
        List<BillDto> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @PostMapping("/generate/{unitsConsumed}/{customerId}")
    public ResponseEntity<BillDto> generateBill(@PathVariable double unitsConsumed,@PathVariable Long customerId) {
        return ResponseEntity.ok(billService.generateBill(unitsConsumed,customerId));
        
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BillDto> updateBill(@PathVariable Long id, @RequestBody BillDto billDto) {
        return ResponseEntity.ok(billService.updateBill(id, billDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
    
//    @GetMapping("/get/cust/{custId}")
//    public ResponseEntity<Bill>getBillByCustId(@PathVariable("custId") int custId){
//    	return ResponseEntity.ok(billService.getBillByCustId(custId));
//    }
    
    
    
    @GetMapping("/get/cust/{custId}")
    public ResponseEntity<List<Bill>> getBillsByCustId(@PathVariable("custId") int custId) {
        List<Bill> bills = billService.getBillByCustId(custId);
        if (bills.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bills);
    }
    @PutMapping("/updatebillstatus/{billId}")
    public ResponseEntity<String> updateBillStatus(@PathVariable Long billId) {
        return ResponseEntity.ok(billService.updateBillStatus(billId));
    }
    
    
}