package com.ebs.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class PaymentDto {
    private Long paymentId;
    private LocalDate paymentDate;
    private Long BillId;
    private String status;
	public PaymentDto() {
		super();
	}
	public PaymentDto(Long paymentId, LocalDate paymentDate, Long billId, String status) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		BillId = billId;
		this.status = status;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Long getBillId() {
		return BillId;
	}
	public void setBillId(Long billId) {
		BillId = billId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}

