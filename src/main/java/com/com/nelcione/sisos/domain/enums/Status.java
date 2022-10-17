package com.com.nelcione.sisos.domain.enums;

public enum Status {
	
	OPEN(0, "OPEN"), PROGRESS(1, "PROGRESS"), CLOSED(2, "CLOSED");
	
	private Integer code;
	private String description;
	
	private Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Status x: Status.values()) {
			if(cod.equals(x.getCode())) { //verifica se o código informado é igual algum código do Status
				return x;
			}
		}
		
		throw new IllegalArgumentException("Staus Inválido!");
	}
}
