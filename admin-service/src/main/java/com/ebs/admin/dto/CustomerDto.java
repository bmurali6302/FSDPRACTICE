package com.ebs.admin.dto;

public class CustomerDto {
	
	private Long customerId;
	private String adharNumber;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String address;
	private String emailId;
	private String password;
	private String gender;
	public CustomerDto() {
		super();
	}
	public CustomerDto(Long customerId, String adharNumber, String firstName, String lastName, String mobileNumber,
			String address, String emailId, String password, String gender) {
		super();
		this.customerId = customerId;
		this.adharNumber = adharNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.emailId = emailId;
		this.password = password;
		this.gender = gender;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", adharNumber=" + adharNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", emailId="
				+ emailId + ", password=" + password + ", gender=" + gender + "]";
	}

	
}
