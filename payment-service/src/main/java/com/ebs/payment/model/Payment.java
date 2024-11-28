package com.ebs.payment.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    private LocalDate paymentDate;
    private Long BillId;
    private String status;
	public Payment() {
		super();
	}
	public Payment(Long paymentId, LocalDate paymentDate, Long billId, String status) {
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
