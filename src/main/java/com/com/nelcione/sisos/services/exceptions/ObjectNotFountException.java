package com.com.nelcione.sisos.services.exceptions;

public class ObjectNotFountException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFountException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ObjectNotFountException(String message) {
		super(message);
		
	}
	
	
	

}
