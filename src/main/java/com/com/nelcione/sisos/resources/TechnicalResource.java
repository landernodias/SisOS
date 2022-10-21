package com.com.nelcione.sisos.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id){ // busca um tecnico pelo seu id
		Technical obj = this.service.findById(id);
		return ResponseEntity.ok().body(new TechnicalDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TechnicalDTO>> findAll(){ // busca todos os tecnicos
		List<Technical> list = service.findAll();
		List<TechnicalDTO> listDTO = list.stream().map(obj -> new TechnicalDTO(obj)).collect(Collectors.toList()); //converte technical em technicalDTO
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TechnicalDTO> create(@RequestBody TechnicalDTO objDTO) {
		Technical newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri(); // URL de acesso a esse novo objeto criado
		return ResponseEntity.created(uri).build();		
	}
}

