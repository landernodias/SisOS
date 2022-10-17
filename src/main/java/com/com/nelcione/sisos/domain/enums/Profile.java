package com.com.nelcione.sisos.domain.enums;

public enum Profile {
		
	ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), TECHNICAL(2, "ROLE_TECHNICAL");
	
	private Integer code; //código do perfil
	private String description; // descrição/regra do perfil
	
	private Profile(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Profile toEnum(Integer cod) {
		if(cod == null) { // verifica se não passou o codigo ou se veio nulo
			return null;
		} 
		
		//Valida o código para cada Profile
		for(Profile x : Profile.values()) {
			if(cod.equals(x.getCode())) { //verifica se o código informado é igual algum código do Profile
				return x;
			}
		}
		
		//caso contrario lança exception
		throw new IllegalArgumentException("Perfil inválido!");
	}
}
