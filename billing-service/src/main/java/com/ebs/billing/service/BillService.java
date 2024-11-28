package com.ebs.billing.service;


import java.util.List;

import com.ebs.billing.dto.BillDto;
import com.ebs.billing.model.Bill;

public interface BillService {

    public BillDto getBill(Long billId);
    public List<BillDto> getAllBills();
    public BillDto generateBill(double unitsConsumed ,Long customerId);
    public BillDto updateBill(Long billId,BillDto billDto);
    public void deleteBill(Long billId);
 
    
    public List<Bill> getBillByCustId(int custId);
    public String  updateBillStatus(Long billId);



}
