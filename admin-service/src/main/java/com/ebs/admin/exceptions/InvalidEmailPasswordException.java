package com.ebs.admin.exceptions;

public class InvalidEmailPasswordException extends RuntimeException {
	String message;

	public InvalidEmailPasswordException() {
		super();
	}

	public InvalidEmailPasswordException(String message) {
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
