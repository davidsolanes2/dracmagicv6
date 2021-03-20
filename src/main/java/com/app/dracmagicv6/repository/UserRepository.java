package com.app.dracmagicv6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dracmagicv6.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
	List<User> findByFechaRegistroNotNull();
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=0 WHERE u.id = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=1 WHERE u.id = :paramIdUsuario")
	int unlock(@Param("paramIdUsuario") int idUsuario);
}
