package com.decathlon.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.decathlon.api.model.responses.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler {
	
	@ExceptionHandler(value = {AdminUserServiceException.class})
	public ResponseEntity<Object> handleAdminUserServiceException(AdminUserServiceException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage (new Date(), ex.getResMessage());
		HttpStatus statusCode = ex.getHttpStatusCode();
		if (statusCode == null) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR ;
		}
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), statusCode) ;
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAdminUserServiceException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage (new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
