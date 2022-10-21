package com.com.nelcione.sisos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.com.nelcione.sisos.domain.dtos.TechnicalDTO;
import com.com.nelcione.sisos.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Technical extends Person{
	private static final long serialVersionUID = 1L;
	@JsonIgnore // corrigi o problema de serialize ou serialização
	@OneToMany(mappedBy = "technical") //um tecnico para muitos chamados
	private List<Called> calleds = new ArrayList<>(); // pode gerar erro de serializer precisa corrigir

	public Technical() {
		super();
		addProfiles(Profile.CLIENT);
	}
	
	public Technical(TechnicalDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.createData = obj.getCreateData();
	}
	

	public Technical(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}
	
}
