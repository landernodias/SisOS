package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Called;
import com.com.nelcione.sisos.domain.Client;
import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.domain.dtos.CalledDTO;
import com.com.nelcione.sisos.domain.enums.Priority;
import com.com.nelcione.sisos.domain.enums.Status;
import com.com.nelcione.sisos.repositories.CalledRepository;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository repository; // faz comunicação com o banco
	
	@Autowired
	private TechnicalService technicalService;
	
	@Autowired
	private ClientService clientService;
	
	public Called findById(Integer id) {
		Optional<Called> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado! ID: " + id));
	}

	public List<Called> findAll() {
		return repository.findAll();
	}

	public Called create(@Valid CalledDTO objDTO) {
		return repository.save(newCalled(objDTO));
	}
	
	//cria ou atualiza um novo chamado
	private Called newCalled(CalledDTO obj) {
		Technical technical = technicalService.findById(obj.getIdTechnical());
		Client client = clientService.findById(obj.getIdClient());
		
		Called called = new Called();
		if(obj.getId() != null) { // se true deseja atualizar o chamado
			called.setId(obj.getId());
		}
		
		//caso contrario cria o chamado
		called.setTechnical(technical);
		called.setClient(client);
		called.setPriority(Priority.toEnum(obj.getPriority()));
		called.setStatus(Status.toEnum(obj.getStatus()));
		called.setTitle(obj.getTitle());
		called.setObservation(obj.getObservation());
		return called;
	}
}
