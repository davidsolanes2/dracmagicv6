package com.app.dracmagicv6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dracmagicv6.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	List<Role> findAll();
	
}
