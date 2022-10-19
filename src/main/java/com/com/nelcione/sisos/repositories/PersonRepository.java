package com.com.nelcione.sisos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.com.nelcione.sisos.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
