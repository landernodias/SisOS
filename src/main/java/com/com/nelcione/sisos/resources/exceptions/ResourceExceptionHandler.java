package com.com.nelcione.sisos.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.com.nelcione.sisos.services.exceptions.DataIntegrityViolationException;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@ControllerAdvice
public class ResourceExceptionHandler {
	// System.currentTimeMillis(): tempo em mile segundos do sistema
	// HttpStatus.NOT_FOUND.value(): status http do erro
	// "Object Not Found": nome do erro ocorrido
	// ex.getMessage(): mensagem de erro
	// request.getRequestURI(): URI que gerou o erro 
	// "Data Breach": violação de dados
	
	@ExceptionHandler(ObjectNotFountException.class)
	public ResponseEntity<StandardError> objectNotFountException(ObjectNotFountException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
						"Object Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> objectNotFountException(DataIntegrityViolationException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
						"Data Breach", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
