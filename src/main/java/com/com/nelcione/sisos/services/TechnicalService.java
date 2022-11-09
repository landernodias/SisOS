package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	public Technical findById(Integer id) {
		Optional<Technical> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado Id: " + id));
	}

	public List<Technical> findAll() {
		return repository.findAll();
	}

	public Technical create(TechnicalDTO objDTO) {
		objDTO.setId(null); // por segurança o id deve ser nulo pois ele é definido via banco
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validByCPFEmail(objDTO);
		Technical newObj = new Technical(objDTO);
		return repository.save(newObj);
	}
	
	public Technical update(Integer id, @Valid TechnicalDTO objDTO) {
		objDTO.setId(id); //seta o id com add passado por paraemetro 
		Technical oldObj = findById(id);
		validByCPFEmail(objDTO);
		oldObj = new Technical(objDTO);
		return repository.save(oldObj); // salva as informações no banco
	}


	public void delete(Integer id) {
		Technical obj = findById(id);
		if(obj.getCalleds().size() > 0) { // verifica se existe ordens de serviço vinculado ao technical que desejo deletar
			throw new DataIntegrityViolationException("O tecnico possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
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
