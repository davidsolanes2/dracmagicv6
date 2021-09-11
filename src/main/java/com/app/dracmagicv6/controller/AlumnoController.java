package com.app.dracmagicv6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dracmagicv6.model.Alumno;
import com.app.dracmagicv6.service.AlumnoService;

@Controller
@RequestMapping(value= "/alumnes")
public class AlumnoController {

	@Autowired
	private AlumnoService serviceAlumno;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	public AlumnoController(AlumnoService serviceAlumno) {
		this.serviceAlumno = serviceAlumno;
	}
	
    @PostMapping("/saveAlumnes")
    public String guardarAlumno(Alumno alumno, RedirectAttributes attributes) {
		serviceAlumno.guardar(alumno);
		return "redirect:/alumnes/indexPaginateAlumnos";
	}
	
	@RequestMapping("/indexPaginateAlumnos")
	public String viewListPaginateAlumnos(Model model) {
		return listByPageAlumnos(model, 1, "nombre", "asc");
	}
	
	@RequestMapping("/indexPaginateAlumnos/{pageNumber}")
	public String listByPageAlumnos(Model model, @PathVariable("pageNumber") int currentPage,
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
			
    @GetMapping("/llistatAlumnesByEdad")
    public String llistatEspecial(Model model) { 	
    	return listAlumnesByEdad(model, 1, "nombre", "asc");    	
	  }

//	  @GetMapping("/llistatAlumnes/{page}/{size}")
//	  public Page<AlumnoDto> findAlumnoByEdad (@PathVariable int page, @PathVariable int size) {
//		  
//		  return serviceAlumno.findAlumnoByEdad(page, size);
//		  
//	  }
	
	
	
    @RequestMapping("/llistatAlumnesByEdad/{pageNumber}")
	  public String listAlumnesByEdad(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		//List<Clase> listClases = serviceClase.listaClases();
		
		Page<Alumno> page = serviceAlumno.findAll(currentPage, sortField, sortDir);

		//long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<Alumno> lista = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		//model.addAttribute("totalItems", totalItems);
		model.addAttribute("listEspecial", lista);
		//model.addAttribute("clase", listClases);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		return "/alumnes/alumnesProves";
	}

}
