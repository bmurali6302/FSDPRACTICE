package com.jss.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employerId;
	private String username;
	private String password;
	private String email;
	private String contactNo;
	private String address;
	private String organizationName;

	public Employer() {
		super();
	}

	public Employer(int employerId, String username, String password, String email, String contactNo, String address,
			String organizationName) {
		super();
		this.employerId = employerId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
		this.organizationName = organizationName;
	}

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
}