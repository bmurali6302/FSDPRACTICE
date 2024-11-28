package com.ebs.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	Admin findByEmailId(String emailId);

}
