package com.com.nelcione.sisos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.nelcione.sisos.domain.Called;
import com.com.nelcione.sisos.domain.dtos.CalledDTO;
import com.com.nelcione.sisos.services.CalledService;

@RestController
@RequestMapping(value = "/calleds")
public class CalledResource {

	@Autowired
	private CalledService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CalledDTO> findById(@PathVariable Integer id) {
		Called obj = service.findById(id);
		return ResponseEntity.ok().body(new CalledDTO(obj));
	}
}
