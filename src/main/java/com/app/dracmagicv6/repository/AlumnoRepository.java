package com.app.dracmagicv6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dracmagicv6.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	
	List<Alumno> findAll();

}
