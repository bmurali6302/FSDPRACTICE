package com.ebs.admin.service;

import org.springframework.stereotype.Service;

import com.ebs.admin.dto.AdminDto;
import com.ebs.admin.entity.Admin;
@Service
public interface AdminService {
	public String registerAdmin(Admin admin);
	public String adminLogin(String emailId,String password);
	public String forgotPassword(AdminDto adminDto);

}
