package com.com.nelcione.sisos.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.com.nelcione.sisos.domain.Client;
import com.com.nelcione.sisos.domain.dtos.ClientDTO;
import com.com.nelcione.sisos.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	   //localhost:8080/client/1
	
	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){ 
		Client obj = this.service.findById(id);
		return ResponseEntity.ok().body(new ClientDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){ // busca todos client
		List<Client> list = service.findAll();
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList()); //converte client em clientDTO
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO) { //cria um client
		Client newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri(); // URL de acesso a esse novo objeto criado
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO objDTO) { // atualiza um client
		Client obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ClientDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){ // deleta um client
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}

