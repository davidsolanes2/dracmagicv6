package com.app.dracmagicv6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumnes")
public class AlumnoController {

//	@Autowired
//	private IUserService serviceUser;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/index")
	public String mostrarIndex() {
		return "alumnes/alumnesIndex";
	}

}
