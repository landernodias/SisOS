package com.com.nelcione.sisos.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long timestap;
	private Integer status;
	private String error;
	private String message;
	private String path; //endpoint que teve a requisição que gerou o erro
	
	public StandardError() {
		super();
	}
	
	public StandardError(Long timestap, Integer status, String error, String message, String path) {
		super();
		this.timestap = timestap;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	public Long getTimestap() {
		return timestap;
	}
	
	public void setTimestap(Long timestap) {
		this.timestap = timestap;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
