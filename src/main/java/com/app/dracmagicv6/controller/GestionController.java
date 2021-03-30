package com.app.dracmagicv6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestio")
public class GestionController {

		@GetMapping("/index")
		public String mostrarIndex() {
			return "gestio/gestioIndex";
		}
}