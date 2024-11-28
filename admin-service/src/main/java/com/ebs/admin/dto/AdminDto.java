package com.ebs.admin.dto;
public class AdminDto {
	private Long adminId;
	private String adminName;
	private String password;
	private String emailId;
	private String confirmpassword;
	public AdminDto() {
		super();
	}
	public AdminDto(Long adminId, String adminName, String password, String emailId, String confirmpassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.emailId = emailId;
		this.confirmpassword = confirmpassword;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@Override
	public String toString() {
		return "AdminDto [adminId=" + adminId + ", adminName=" + adminName + ", password=" + password + ", emailId="
				+ emailId + ", confirmpassword=" + confirmpassword + "]";
	}
	
	
	
}
