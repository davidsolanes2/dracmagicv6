package com.app.dracmagicv6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dracmagicv6.model.Clase;

public interface ClaseRepository extends JpaRepository<Clase, Integer>{

	List<Clase> findAll();
	
}
