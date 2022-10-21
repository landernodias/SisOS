package com.com.nelcione.sisos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.repositories.TechnicalRepository;
import com.com.nelcione.sisos.services.exceptions.ObjectNotFountException;

@Service
public class TechnicalService {
	
	@Autowired
	private TechnicalRepository repository;
	
	public Technical findById(Integer id) {
		Optional<Technical> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado Id: " + id));
	}

	public List<Technical> findAll() {
		return repository.findAll();
	}
}
