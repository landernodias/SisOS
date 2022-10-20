package com.com.nelcione.sisos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.domain.dtos.TechnicalDTO;
import com.com.nelcione.sisos.services.TechnicalService;

//REST -> Recursos

@RestController
@RequestMapping(value = "/technical") //quando for acessar um recurso relacionado a tecnicos usa o /technical  
public class TechnicalResource {
	
	   //localhost:8080/technical/1
	
	@Autowired
	private TechnicalService service;
	
	// ResponseEntity: controla todas resposta HTTP
	@GetMapping(value = "/{id}") // por ser uma busca usase o getMapping   /{id}: informa que recebe um path do tipo variable possui parametros
	public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id){
		Technical obj = this.service.findById(id);
		return ResponseEntity.ok().body(new TechnicalDTO(obj));
	}
}

