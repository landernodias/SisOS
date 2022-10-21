package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Person;
import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.domain.dtos.TechnicalDTO;
import com.com.nelcione.sisos.repositories.PersonRepository;
import com.com.nelcione.sisos.repositories.TechnicalRepository;
import com.com.nelcione.sisos.services.exceptions.DataIntegrityViolationException;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@Service
public class TechnicalService {
	
	@Autowired
	private TechnicalRepository repository;
	@Autowired
	private PersonRepository personRepository;
	
	public Technical findById(Integer id) {
		Optional<Technical> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado Id: " + id));
	}

	public List<Technical> findAll() {
		return repository.findAll();
	}

	public Technical create(TechnicalDTO objDTO) {
		objDTO.setId(null); // por segurança o id deve ser nulo pois ele é definido via banco
		validByCPFEmail(objDTO);
		Technical newObj = new Technical(objDTO);
		return repository.save(newObj);
	}

	//cpf ou email já cadastrado no banco de dados
	private void validByCPFEmail(TechnicalDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) { // se ele for presente ele existe e se o id de quem estamos passando for diferente da pessoa que está no banco não pode criar
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = personRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) { // se ele for presente ele existe e se o id de quem estamos passando for diferente da pessoa que está no banco não pode criar
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}

	}
} 
