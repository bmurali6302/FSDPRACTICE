package com.ebs.billing.mapper;

import com.ebs.billing.dto.BillDto;
import com.ebs.billing.model.Bill;

public class BillMapper {

    // Convert Bill JPA Entity into BillDto
    public static BillDto mapToBillDto(Bill bill) {
        return new BillDto(
                bill.getBillId(),
                bill.getBillDate(),
                bill.getBillDueDate(),
                bill.getUnitsConsumed(),
                bill.getBillAmount(),
                bill.getBillStatus(),
                bill.getCustomerId()
               
        );
    }

    // Convert BillDto into Bill JPA Entity
    public static Bill mapToBill(BillDto billDto) {
        return new Bill(
                billDto.getBillId(),
                billDto.getBillDate(),
                billDto.getBillDueDate(),
                billDto.getUnitsConsumed(),
                billDto.getBillAmount(),
                billDto.getBillStatus(),
                billDto.getCustomerId()
        );
    }
}
