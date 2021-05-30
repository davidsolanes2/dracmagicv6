package com.app.dracmagicv6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dracmagicv6.model.Role;
import com.app.dracmagicv6.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	public void guardar(Role Role) {
		roleRepo.save(Role);
	}
}
