package com.ebs.admin.globalhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GolbalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(com.ebs.admin.exceptions.AdminNotRegisterException.class)
	public ResponseEntity<String> AdminNotRegisterException(com.ebs.admin.exceptions.AdminNotRegisterException ex){
		System.out.println(ex);
		return new ResponseEntity<String>("Admin id not found",HttpStatus.OK);
	}
	
		@ExceptionHandler(com.ebs.admin.exceptions.InvalidEmailPasswordException.class)
		public ResponseEntity<String> InvalidEmailPasswordException(com.ebs.admin.exceptions.InvalidEmailPasswordException ex){
			System.out.println(ex);
			return new ResponseEntity<String>("invalid Password",HttpStatus.OK);
		}
		
		
		ApiError error=new ApiError();
		@ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleException(Exception ex) {
			error.setStatus(HttpStatus.NOT_FOUND);
			error.setTimestamp(LocalDateTime.now());
			error.setMessage(ex.getMessage());
	        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	    }	

}
