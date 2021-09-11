package com.app.dracmagicv6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dracmagicv6.model.Clase;
import com.app.dracmagicv6.repository.ClaseRepository;

@Service
public class ClaseService implements IClaseService{

	@Autowired
	private ClaseRepository claseRepo;
	
	public void guardar(Clase Clase) {
		claseRepo.save(Clase);
	}
	
	@Override
	public List<Clase> listaClases() {
		
		return claseRepo.findAll();
	} 

	
}
