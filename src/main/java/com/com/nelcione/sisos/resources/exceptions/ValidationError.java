package com.com.nelcione.sisos.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMassage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestap, Integer status, String error, String message, String path) {
		super(timestap, status, error, message, path);
	}

	public List<FieldMassage> getErrors() {
		return errors;
	}

	//adciona os erros a uma lista de erros
	public void addError(String fildName, String message) {
		this.errors.add(new FieldMassage(fildName, message));
	}
}
