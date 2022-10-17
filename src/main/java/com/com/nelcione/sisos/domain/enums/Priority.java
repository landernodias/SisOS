package com.com.nelcione.sisos.domain.enums;

public enum Priority {
	LOW(0, "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");
	
	private Integer code;
	private String description;
	
	private Priority(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Priority toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Priority x: Priority.values()) {
			if(cod.equals(x.getCode())) { //verifica se o código informado é igual algum código do Priority
				return x;
			}
		}
		throw new IllegalArgumentException("Propriedade Inválida!");
	}
}
