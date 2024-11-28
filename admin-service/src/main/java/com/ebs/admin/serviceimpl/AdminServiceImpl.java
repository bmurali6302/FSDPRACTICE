package com.ebs.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.admin.dto.AdminDto;
import com.ebs.admin.entity.Admin;
import com.ebs.admin.exceptions.AdminNotRegisterException;
import com.ebs.admin.exceptions.InvalidEmailPasswordException;
import com.ebs.admin.repository.AdminRepository;
import com.ebs.admin.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	@Override
	public String registerAdmin(Admin admin) {
	
		 adminRepository.save(admin);
		 return "Admin Registered Successfully";
	}
	@Override
	public String adminLogin(String emailId, String password) {
			Admin admin=adminRepository.findByEmailId(emailId);
			if(admin!=null && admin.getPassword().equals(password))
				return "Login Successfully";
			else if(admin==null) {
				throw new AdminNotRegisterException("Admin id is not register");
				
			}
			throw new InvalidEmailPasswordException("Invalid_password");
		
	}
	
		@Override
	public String forgotPassword(AdminDto adminDto) {
		
            Admin admin=adminRepository.findByEmailId(adminDto.getEmailId());
		if(admin==null) {
			throw new AdminNotRegisterException("Admin id is not register");
		}else if(!adminDto.getPassword().matches("^[a-zA-Z0-9_@#]{8,14}$")) {
			throw new InvalidEmailPasswordException("Invalid_password");
		}else if(!adminDto.getConfirmpassword().matches(adminDto.getPassword())) {
			throw new InvalidEmailPasswordException("Invalid_Password");
		}
		else {
			admin.setPassword(adminDto.getPassword());
			adminRepository.save(admin);
			return "Password Reset Succesfully";
		}		

	}

}
