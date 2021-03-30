package com.app.dracmagicv6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutors")
public class TutorController {

		@GetMapping("/index")
		public String mostrarIndex() {
			return "tutors/tutorsIndex";
		}
		
}