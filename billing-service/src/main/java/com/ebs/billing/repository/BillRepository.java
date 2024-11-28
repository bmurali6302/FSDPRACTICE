package com.ebs.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ebs.billing.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
	
//	@Query(value="select * from bill where customer_id=:custId",nativeQuery=true)
//	public Bill getBillByCustomerId(@Param("custId") int custId);
	
	
	@Query(value = "SELECT * FROM bill WHERE customer_id = :custId", nativeQuery = true)
    public List<Bill> getBillsByCustomerId(@Param("custId") int custId);

}
