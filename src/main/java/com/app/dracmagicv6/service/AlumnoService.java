package com.app.dracmagicv6.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dracmagicv6.model.Alumno;
import com.app.dracmagicv6.repository.AlumnoRepository;


@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepo;
	
	public void guardar(Alumno Alumno) {
		alumnoRepo.save(Alumno);
	}
	
	public Page<Alumno> findAll(int pageNumber, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNumber -1, 5,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		return alumnoRepo.findAll(pageable);
	}
	
//	public Page<Alumno> findAlumnoGetByEdad(int pageNumber, String sortField, String sortDir) {
//		Pageable pageable = PageRequest.of(pageNumber - 1, 5,
//				 sortDir.equals("asc")	? Sort.by(sortField).ascending()
//						 				: Sort.by(sortField).descending()
//			);		 
//		return alumnoRepo.findAlumnoGetByEdad(pageable);
//	}
	
	
//	public List<Alumno> findAllByEdad(PageRequest pageRequest) {
//		Page<Alumno> alumnoList = alumnoRepo.findAllByEdad(pageRequest);
//		return alumnoList.getContent();
//	}
	
	
//	public Page<AlumnoDto> findAlumnoByEdad(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nombre"));
//		
//		List<AlumnoDto> alumnos = alumnoRepo.findAlumnoByEdad(pageable);
//		Page<AlumnoDto> pageOfAlumnos = new PageImpl(alumnos, pageable,
//				alumnos.isEmpty() ? 0 : alumnos.get(0).getEdad());
//		
//		return pageOfAlumnos;
//	}

	public Alumno getAlumnoById(Integer id) {
		Optional<Alumno> optional = alumnoRepo.findById(id);
		Alumno alumno = null;
		if(optional.isPresent()) {
			alumno = optional.get();
		}else {
			throw new RuntimeException(" Alumno no encontrado ");
		}
		return alumno;
	}

	
}