package com.ebs.billing.service.impl;

import com.ebs.billing.client.CustomerService;
import com.ebs.billing.dto.BillDto;
import com.ebs.billing.dto.CustomerDto;
import com.ebs.billing.exception.BillNotFoundException;
import com.ebs.billing.mapper.BillMapper;
import com.ebs.billing.model.Bill;
import com.ebs.billing.repository.BillRepository;
import com.ebs.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    
    
    @Autowired
    CustomerService customerService;

    @Override
    public BillDto getBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException("Bill not found with ID: " + billId));
        return BillMapper.mapToBillDto(bill);
    }

    @Override
    public List<BillDto> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream()
                .map(BillMapper::mapToBillDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillDto generateBill(double unitsConsumed,Long customerId) {
    	
    	ResponseEntity<CustomerDto> customer=customerService.viewCustomerByCustomerId(customerId);
    	if(customer==null) {
    		throw new BillNotFoundException("Bill id not found");
    		
    	}
        BillDto billDto = new BillDto();
        billDto.setBillDate(LocalDate.now());
        billDto.setBillDueDate(LocalDate.now().plusDays(30));
        billDto.setBillStatus("PENDING");
        billDto.setUnitsConsumed(unitsConsumed);
        billDto.setBillAmount(unitsConsumed * 5.0);
        billDto.setCustomerId(customerId);
        Bill bill = BillMapper.mapToBill(billDto);
        Bill savedBill = billRepository.save(bill);

        return BillMapper.mapToBillDto(savedBill);
    }

    @Override
    public BillDto updateBill(Long billId, BillDto billDto) {
        Optional<Bill> optionalBill = billRepository.findById(billId);
        if (optionalBill.isPresent()) {
            Bill existingBill = optionalBill.get();
            existingBill.setBillDate(billDto.getBillDate());
            existingBill.setBillDueDate(billDto.getBillDueDate());
            existingBill.setUnitsConsumed(billDto.getUnitsConsumed());
            existingBill.setBillAmount(billDto.getBillAmount());
            existingBill.setBillStatus(billDto.getBillStatus());

            Bill updatedBill = billRepository.save(existingBill);
            
            return BillMapper.mapToBillDto(updatedBill);
        } else {
            throw new BillNotFoundException("Bill not found with ID: " + billId);
        }
    }

    @Override
    public void deleteBill(Long billId) {
        if (billRepository.existsById(billId)) {
            billRepository.deleteById(billId);
        } else {
            throw new BillNotFoundException("Bill not found with ID: " + billId);
        }
    }
        
    public List<Bill> getBillByCustId(int custId) {
        List<Bill> bills = billRepository.getBillsByCustomerId(custId);
        return bills;
    }


	@Override
	public String updateBillStatus(Long billId) {
		Optional<Bill> optionalBill = billRepository.findById(billId);
        if (optionalBill.isPresent()) {
            Bill existingBill = optionalBill.get();
            existingBill.setBillStatus("PAID");
            billRepository.save(existingBill);
            return "bill updated";
        } else {
            throw new BillNotFoundException("Bill not found with ID: " + billId);
        }
	}
    
    

}
