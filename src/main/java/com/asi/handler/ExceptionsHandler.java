package com.asi.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<Object> handleRessourceNotFound(RuntimeException exception, WebRequest request) {
		String bodyMessage = exception.getMessage();
		return handleExceptionInternal(exception, bodyMessage, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
	}
}
