package com.app.dracmagicv6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dracmagicv6.service.IUserService;

@Controller
@RequestMapping("/alumnes")
public class AlumnoController {

	@Autowired
	private IUserService serviceUser;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/index")
	public String mostrarIndex() {
		return "alumnes/alumnesIndex";
	}

}
