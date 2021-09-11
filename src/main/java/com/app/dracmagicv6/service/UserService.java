package com.app.dracmagicv6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dracmagicv6.model.Clase;
import com.app.dracmagicv6.model.User;
import com.app.dracmagicv6.repository.ClaseRepository;
import com.app.dracmagicv6.repository.UserRepository;

@Service
public class UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClaseRepository claseRepo;
	
	public void guardar(User User) {  // OK
		userRepo.save(User);
	}

	public void deleteUserById(Integer id) {
		userRepo.deleteById(id);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	
	// new version pagination
	public Page<User> findAll(int pageNumber, String sortField, String sortDir) {

		 Pageable pageable = PageRequest.of(pageNumber - 1, 5,
				 sortDir.equals("asc")	? Sort.by(sortField).ascending()
						 				: Sort.by(sortField).descending()
			);		 
							
		return userRepo.findAll(pageable);
	}
	
	public User buscarPorUsername(String username) {
		return userRepo.findByUsername(username);
	}


	public List<User> buscarRegistrados() {		
		return userRepo.findByFechaRegistroNotNull();
	}

	@Transactional
	public int bloquear(int idUser) {
		int rows = userRepo.lock(idUser);
		return rows;
	}

	@Transactional
	public int activar(int idUser) {
		int rows = userRepo.unlock(idUser);
		return rows;
	}


	public User getUserById(Integer id) {
		Optional<User> optional = userRepo.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		}else {
			throw new RuntimeException(" Usuario no encontrado ");
		}
		return user;
	}

//	public List<User> listaClase() {
//		return userRepo.findAll();
//	}

	public Clase insertaClase(Clase obj) {
		return claseRepo.save(obj);
	}

}
