package com.com.nelcione.sisos.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.com.nelcione.sisos.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

//anotation JPA
@Entity
public abstract class Person implements Serializable { // Serializable: permite cria uma sequencia de bytes da instancia para que possa ser trafegado em redes
	private static final long serialVersionUID = 1L;
	
	@Id //fala que esse campo é id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // como esse id será gerado
	protected Integer id;
	protected String name;
	
	@Column(unique = true) // o campo CPF é unico
	protected String cpf;
	
	@Column(unique = true) // o compo email é unico
	protected String email;
	protected String password;
	
	@ElementCollection(fetch = FetchType.EAGER) // assegura que a lista de perfil venha com o usuario
	@CollectionTable(name = "PROFILE")
	protected Set<Integer> profiles = new HashSet<>(); // Lista de perfil do tipo SET(chave, valor) //evita newPonterException quando inicia com hashset// não vai ter dois perfis igual dentro da lista
	
	@JsonFormat(pattern = "dd/MM/yyyy")//formato da data na base de dados
	protected LocalDate createData = LocalDate.now(); // pega a instancia atual de data que esse objeto foi criado
	
	//todo usuario cria precisa ter pelo menos o perfil de cliente
	public Person() {
		super();
		addProfiles(Profile.CLIENT);// cria por padrão todos usuario com perfil de cliente
	}

	public Person(Integer id, String name, String cpf, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		addProfiles(Profile.CLIENT);// cria por padrão todos usuario com perfil de cliente
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
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet()); // pega lista de perfil e mapeia os perfil
	}

	public void addProfiles(Profile profile) { // adciona um perfil unico a lista de perfil
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreateData() {
		return createData;
	}

	public void setCreateData(LocalDate createData) {
		this.createData = createData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
}
