package com.app.dracmagicv6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.dracmagicv6.model.User;

public interface IUserService {
	
	void guardar(User user);
	void eliminar(Integer idUser);
	
	List<User> buscarTodos();
	Page<User> buscarTodos(Pageable page);
	List<User> buscarRegistrados();
	List<User> getAllUsers();
	Page<User> getAllUsers(Pageable page);
	
	User buscarPorId(Integer idUser);
	User buscarPorUsername(String username);
	
	int bloquear(int idUser);
	int activar(int idUser);
}
