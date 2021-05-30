package com.app.dracmagicv6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dracmagicv6.model.Alumno;
import com.app.dracmagicv6.service.AlumnoService;

@Controller
@RequestMapping("/alumnes")
public class AlumnoController {

	@Autowired
	private AlumnoService serviceAlumno;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/indexPaginateAlumnos")
	public String viewListPaginateAlumnos(Model model) {
		return listByPage(model, 1, "nombre", "asc");
	}
	
	@RequestMapping("/indexPaginateAlumnos/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {
		
		Page<Alumno> page = serviceAlumno.findAll(currentPage, sortField, sortDir);
		
		int totalPages = page.getTotalPages();
		
		List<Alumno> lista = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listAlumno", lista);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		
		return "alumnes/alumnesIndex";
	}
	
//	@GetMapping("/index")
//	public String mostrarIndex() {
//		return "alumnes/alumnesIndex";
//	}
	
//	@GetMapping("/saveAlumnes")
//	public String mostrarForm(Alumno alumno) {
//		return "alumnes/alumnesForm";
//	}
	
//	@PostMapping("/saveAlumnes")
//	public String guardarAlumno(Alumno alumno, RedirectAttributes attributes) {
//		serviceAlumno.guardar(alumno);
//		return "redirect:/alumnes/alumnesIndex";
//	}

}
