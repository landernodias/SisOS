package com.com.nelcione.sisos.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.com.nelcione.sisos.domain.Called;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CalledDTO implements Serializable {	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openingDate = LocalDate.now(); //data de abertura
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cosingDate = LocalDate.now(); //data de fechamento
	
	@NotNull(message = "O campo PRIORIDADE é Requerido!")
	private Integer priority;
	
	@NotNull(message = "O campo STATUS é Requerido!")
	private Integer status;
	
	@NotNull(message = "O campo TITULO é Requerido!")
	private String title;
	
	@NotNull(message = "O campo OBSERVAÇÕES é Requerido!")
	private String observation;
	
	@NotNull(message = "O campo TECNICO é Requerido!")
	private Integer idTechnical;
	
	@NotNull(message = "O campo CLIENTE é Requerido!")
	private Integer idClient;
	
	private String nameTechnical;
	private String nameClient;
	
	public CalledDTO() {
		super();
	}

	public CalledDTO(Called obj) {
		super();
		this.id = obj.getId();
		this.openingDate = obj.getOpeningDate();
		this.cosingDate = obj.getCosingDate();
		this.priority = obj.getPriority().getCode();
		this.status = obj.getStatus().getCode();
		this.title = obj.getTitle();
		this.observation = obj.getObservation();
		this.idTechnical = obj.getTechnical().getId();
		this.idClient = obj.getClient().getId();
		this.nameTechnical = obj.getTechnical().getName();
		this.nameClient = obj.getClient().getName();
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public Integer getIdTechnical() {
		return idTechnical;
	}

	public void setIdTechnical(Integer idTechnical) {
		this.idTechnical = idTechnical;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getNameTechnical() {
		return nameTechnical;
	}

	public void setNameTechnical(String nameTechnical) {
		this.nameTechnical = nameTechnical;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}	
}
