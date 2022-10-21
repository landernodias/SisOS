package com.com.nelcione.sisos.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TechnicalDTO implements Serializable {	
	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME é requerido!")
	protected String name;
	@NotNull(message = "O campo CPF é requerido!")
	protected String cpf;
	@NotNull(message = "O campo E-MAIL é requerido!")
	protected String email;
	@NotNull(message = "O campo SENHA é requerido!")
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat
	protected LocalDate createData = LocalDate.now();

	public TechnicalDTO() {
		super();
		addProfiles(Profile.CLIENT);
	}

	public TechnicalDTO(Technical obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.createData = obj.getCreateData();
		addProfiles(Profile.CLIENT);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfiles(Profile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreateData() {
		return createData;
	}

	public void setCreateData(LocalDate createData) {
		this.createData = createData;
	} 	
}
