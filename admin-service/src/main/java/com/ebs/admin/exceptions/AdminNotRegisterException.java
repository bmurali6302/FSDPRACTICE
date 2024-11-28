package com.ebs.admin.exceptions;

public class AdminNotRegisterException extends RuntimeException {
	String message;

	public AdminNotRegisterException() {
		super();
	}

	public AdminNotRegisterException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
