package com.ebs.billing;

import com.ebs.billing.client.CustomerService;
import com.ebs.billing.dto.BillDto;
import com.ebs.billing.dto.CustomerDto;
import com.ebs.billing.exception.BillNotFoundException;
import com.ebs.billing.model.Bill;
import com.ebs.billing.repository.BillRepository;
import com.ebs.billing.service.impl.BillServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private BillServiceImpl billService;

    // Example of a Bill and BillDto setup
    private Bill bill;
    private BillDto billDto;

    @BeforeEach
    void setUp() {
       bill = new Bill();
       billDto = new BillDto();
       // Initialize your bill and billDto objects here
    }

    @Test
    public void testGetBill_WhenBillExists() {
       when(billRepository.findById(1L)).thenReturn(Optional.of(bill));
       BillDto result = billService.getBill(1L);
       assertNotNull(result);
       // Add more assertions to verify the BillDto fields
    }

    @Test
    public void testGetBill_WhenBillDoesNotExist() {
       when(billRepository.findById(1L)).thenReturn(Optional.empty());
       assertThrows(BillNotFoundException.class, () -> billService.getBill(1L));
    }


    @Test
    public void testGetAllBills() {
       List<Bill> bills = Arrays.asList(bill);
       when(billRepository.findAll()).thenReturn(bills);
       List<BillDto> result = billService.getAllBills();
       assertFalse(result.isEmpty());
       assertEquals(1, result.size());
       // Add more assertions to verify the BillDto fields
    }


    @Test
    public void testGenerateBill_WithValidCustomer() {
       when(customerService.viewCustomerByCustomerId(1L)).thenReturn(new ResponseEntity<>(new CustomerDto(), HttpStatus.OK));
       when(billRepository.save(any(Bill.class))).thenReturn(bill);
       BillDto result = billService.generateBill(100.0, 1L);
       assertNotNull(result);
       // Assertions to check bill calculations and fields
    }

    @Test
    public void testGenerateBill_WithInvalidCustomer() {
       when(customerService.viewCustomerByCustomerId(1L)).thenReturn(null);
       assertThrows(BillNotFoundException.class, () -> billService.generateBill(100.0, 1L));
    }



    @Test
    public void testUpdateBill_WhenBillExists() {
       when(billRepository.findById(1L)).thenReturn(Optional.of(bill));
       when(billRepository.save(any(Bill.class))).thenReturn(bill);
       BillDto result = billService.updateBill(1L, new BillDto());
       assertNotNull(result);
       // Assertions to verify updated fields
    }

    @Test
    public void testUpdateBill_WhenBillDoesNotExist() {
       when(billRepository.findById(1L)).thenReturn(Optional.empty());
       assertThrows(BillNotFoundException.class, () -> billService.updateBill(1L, new BillDto()));
    }


    @Test
    public void testDeleteBill_WhenBillExists() {
       when(billRepository.existsById(1L)).thenReturn(true);
       assertDoesNotThrow(() -> billService.deleteBill(1L));
    }

    @Test
    public void testDeleteBill_WhenBillDoesNotExist() {
       when(billRepository.existsById(1L)).thenReturn(false);
       assertThrows(BillNotFoundException.class, () -> billService.deleteBill(1L));
    }
}