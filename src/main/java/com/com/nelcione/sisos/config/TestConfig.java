package com.com.nelcione.sisos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.com.nelcione.sisos.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanceDB() {
		this.dbService.instanceDB(); // sobe o metodo das instancia DBService
	}
}
