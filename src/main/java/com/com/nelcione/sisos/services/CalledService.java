package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Called;
import com.com.nelcione.sisos.repositories.CalledRepository;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository repository; // faz comunicação com o banco
	
	public Called findById(Integer id) {
		Optional<Called> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado! ID: " + id));
	}

	public List<Called> findAll() {
		return repository.findAll();
	}
}
