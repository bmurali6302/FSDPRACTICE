package com.ebs.payment.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ebs.payment.excepion.PaymentNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
    ApiError error = new ApiError();

    @ExceptionHandler({PaymentNotFound.class})
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {

        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.valueOf(400));
    }
    
    
    //ApiError error=new ApiError();
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }	

}
