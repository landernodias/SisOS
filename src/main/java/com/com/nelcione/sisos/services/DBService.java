package com.com.nelcione.sisos.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.nelcione.sisos.domain.Called;
import com.com.nelcione.sisos.domain.Client;
import com.com.nelcione.sisos.domain.Technical;
import com.com.nelcione.sisos.domain.enums.Priority;
import com.com.nelcione.sisos.domain.enums.Profile;
import com.com.nelcione.sisos.domain.enums.Status;
import com.com.nelcione.sisos.repositories.CalledRepository;
import com.com.nelcione.sisos.repositories.ClientRepository;
import com.com.nelcione.sisos.repositories.TechnicalRepository;

@Service
public class DBService {
	
	@Autowired // injeção de dependência
	private TechnicalRepository technicalRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRepository;

	public void instanceDB() {
		//cria um tecnico para teste
				Technical tech1 = new Technical(null, "Nelcione Dias", "83732478661", "nelcionedias@gmail.com", "1234");
				tech1.addProfiles(Profile.ADMIN);
				
				Client cli1 = new Client(null, "Anna Claudia", "05526321711", "annaclaudia@gmail.com", "1234");
				Called c1 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 01", "first Called", tech1, cli1);
				
				technicalRepository.saveAll(Arrays.asList(tech1));
				clientRepository.saveAll(Arrays.asList(cli1));
				calledRepository.saveAll(Arrays.asList(c1));
	}
}
