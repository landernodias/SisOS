package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Client;
import com.com.nelcione.sisos.domain.Person;
import com.com.nelcione.sisos.domain.dtos.ClientDTO;
import com.com.nelcione.sisos.repositories.ClientRepository;
import com.com.nelcione.sisos.repositories.PersonRepository;
import com.com.nelcione.sisos.services.exceptions.DataIntegrityViolationException;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	@Autowired
	private PersonRepository personRepository;
	
	public Client findById(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado Id: " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client create(ClientDTO objDTO) {
		objDTO.setId(null);
		validByCPFEmail(objDTO);
		Client newObj = new Client(objDTO);
		return repository.save(newObj);
	}
	
	public Client update(Integer id, @Valid ClientDTO objDTO) {
		objDTO.setId(id); 
		Client oldObj = findById(id);
		validByCPFEmail(objDTO);
		oldObj = new Client(objDTO);
		return repository.save(oldObj); 
	}


	public void delete(Integer id) {
		Client obj = findById(id);
		if(obj.getCalleds().size() > 0) { 
			throw new DataIntegrityViolationException("O Cliente possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validByCPFEmail(ClientDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = personRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}

	}

} 
