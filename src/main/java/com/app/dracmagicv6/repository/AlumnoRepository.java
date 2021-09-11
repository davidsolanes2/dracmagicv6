package com.app.dracmagicv6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dracmagicv6.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	
	List<Alumno> findAll();

//	@Transactional
//	@Query(value = "SELECT nombre, apellidos, direccion, "
//			+ "(SELECT TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE())) AS edad FROM alumno",
//			nativeQuery = true)
//	List<AlumnoDto> findAlumnoByEdad(Pageable pageable);
	
	//Page<Alumno> findAlumnoGetByEdad(Pageable pageable);

	


}
