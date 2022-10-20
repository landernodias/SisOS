package com.com.nelcione.sisos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.repositories.TechnicalRepository;

@Service
public class TechnicalService {
	
	@Autowired
	private TechnicalRepository repository;
	
	public Technical findById(Integer id) {
		Optional<Technical> obj = repository.findById(id);
		return obj.orElse(null); // se não encontrar o objeto retorna nulo
	}

}
