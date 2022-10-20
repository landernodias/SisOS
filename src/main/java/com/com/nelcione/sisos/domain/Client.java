package com.com.nelcione.sisos.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.com.nelcione.sisos.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client extends Person{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore // evita problema se serialização
	@OneToMany(mappedBy = "client")
	private List<Called> calleds = new ArrayList<>();

	public Client() {
		super();
		addProfiles(Profile.CLIENT);
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfiles(Profile.CLIENT);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}
	
	
	
}
