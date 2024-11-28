package com.ebs.billing.handler;

import com.ebs.billing.exception.BillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    ApiError error = new ApiError();

    @ExceptionHandler({BillNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {

        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.valueOf(400));
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }	
}
