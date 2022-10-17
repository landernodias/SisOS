package com.com.nelcione.sisos.domain;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person{

	private List<Called> calleds = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}
	
	
	
}
