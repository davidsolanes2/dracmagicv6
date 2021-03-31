package com.app.dracmagicv6.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dracmagicv6.model.User;
import com.app.dracmagicv6.repository.UserRepository;
import com.app.dracmagicv6.service.IUserService;

@Service
public class UserServiceJpa implements IUserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void guardar(User User) {
		userRepo.save(User);
	}

	@Override
	public void eliminar(Integer idUser) {
		userRepo.deleteById(idUser);
	}

	@Override
	public List<User> buscarTodos() {
		return userRepo.findAll();
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User buscarPorId(Integer idUser) {
		Optional<User> optional = userRepo.findById(idUser);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public User buscarPorUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> buscarRegistrados() {		
		return userRepo.findByFechaRegistroNotNull();
	}

	@Transactional
	@Override
	public int bloquear(int idUser) {
		int rows = userRepo.lock(idUser);
		return rows;
	}

	@Transactional
	@Override
	public int activar(int idUser) {
		int rows = userRepo.unlock(idUser);
		return rows;
	}


}
