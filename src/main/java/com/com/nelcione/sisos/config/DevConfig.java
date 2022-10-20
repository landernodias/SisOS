package com.com.nelcione.sisos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.com.nelcione.sisos.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public boolean instanceDB() {
		if(value.equals("create")) { // se for create instancia a carga inicial
			this.dbService.instanceDB();
		}
		return false; // morre o esquema
	}
}
