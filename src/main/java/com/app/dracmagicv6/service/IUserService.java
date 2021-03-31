package com.app.dracmagicv6.service;

import java.util.List;

import com.app.dracmagicv6.model.User;

public interface IUserService {
	
	void guardar(User user);
	void eliminar(Integer idUser);
	
	List<User> buscarTodos();
	List<User> buscarRegistrados();
	List<User> getAllUsers();
	
	User buscarPorId(Integer idUser);
	User buscarPorUsername(String username);
	
	int bloquear(int idUser);
	int activar(int idUser);
}
