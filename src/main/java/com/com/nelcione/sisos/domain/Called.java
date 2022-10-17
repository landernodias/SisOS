package com.com.nelcione.sisos.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.com.nelcione.sisos.domain.enums.Priority;
import com.com.nelcione.sisos.domain.enums.Status;

public class Called {
	
	private Integer id;
	private LocalDate openingDate = LocalDate.now(); //data de abertura
	private LocalDate cosingDate = LocalDate.now(); //data de fechamento
	private Priority priority;
	private Status status;
	private String title;
	private String observation;
	
	private Technical technical;
	private Client client;
	
	public Called() {
		super();
	}

	public Called(Integer id, Priority priority, Status status, String title, String observation, Technical technical,
			Client client) {
		super();
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.observation = observation;
		this.technical = technical;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDate getCosingDate() {
		return cosingDate;
	}

	public void setCosingDate(LocalDate cosingDate) {
		this.cosingDate = cosingDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Technical getTechnical() {
		return technical;
	}

	public void setTechnical(Technical technical) {
		this.technical = technical;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Called other = (Called) obj;
		return Objects.equals(id, other.id);
	}
}
