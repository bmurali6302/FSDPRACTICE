package com.ebs.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ebs.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query(value="select * from payment where bill_id = :billId",nativeQuery=true)
	public Optional<Payment> getByBillId(@Param("billId") Long billId);

}
