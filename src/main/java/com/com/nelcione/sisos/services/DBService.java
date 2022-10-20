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
import com.com.nelcione.sisos.repositories.PersonRepository;

@Service
public class DBService {
	
//	@Autowired // injeção de dependência
//	private TechnicalRepository technicalRepository;
//	@Autowired
//	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRepository;
	@Autowired
	private PersonRepository personRepository;

	public void instanceDB() {
		//cria um tecnico para teste
				Technical tech1 = new Technical(null, "Nelcione Dias", "83732478661", "nelcionedias@gmail.com", "1234");
				tech1.addProfiles(Profile.ADMIN);
				Technical tech2 = new Technical(null, "Danilo Oliveira", "14257896324", "danilooliveira@gmail.com", "1425");
				Technical tech3 = new Technical(null, "Bruno Moura", "47351596086", "bruno@gmail.com", "1425");
				Technical tech4 = new Technical(null, "Gustavo Borges", "06475125698", "gustavo@gmail.com", "1425");
				Technical tech5 = new Technical(null, "Eduardo Montana", "98725812305", "edumontana@gmail.com", "1425");
				
				Client cli1 = new Client(null, "Anna Claudia", "05526321711", "annaclaudia@gmail.com", "1234");
				Client cli2 = new Client(null, "Luzia Barbosa", "74596502105", "luziabarbosa@gmail.com", "1234");
				Client cli3 = new Client(null, "Adeilson Barbosa", "05051473611", "adeilsonbarbosa@gmail.com", "1234");
				Client cli4 = new Client(null, "Nelson Fernandes", "11112547801", "nelson234@gmail.com", "1234");
				Client cli5 = new Client(null, "Alzira Belmira", "3649851", "alzirabelmira@gmail.com", "1234");
				
				
				Called c1 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 01", "first Called 1", tech1, cli1);
				Called c2 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 02", "second Called 2", tech1, cli2);
				Called c3 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 03", "third Called 3", tech2, cli3);
				Called c4 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 04", "bedroom Called 4", tech3, cli3);
				Called c5 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 05", "fifth Called 5", tech2, cli1);
				Called c6 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Called 06", "sixth Called 6", tech1, cli5);
				
				personRepository.saveAll(Arrays.asList(tech1, tech2, tech3, tech4, tech5, cli1, cli2, cli3, cli4, cli5));
				calledRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
}
