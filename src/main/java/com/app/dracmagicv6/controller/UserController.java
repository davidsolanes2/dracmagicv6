package com.app.dracmagicv6.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dracmagicv6.model.Clase;
import com.app.dracmagicv6.model.Role;
import com.app.dracmagicv6.model.User;
import com.app.dracmagicv6.service.ClaseService;
import com.app.dracmagicv6.service.UserService;

@Controller
@RequestMapping("/usuaris")
public class UserController {
	
	@Autowired
	private UserService serviceUser;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClaseService serviceClase;
	
	@GetMapping("/saveUsuaris")
	public String registrarUser(User user) {
		return "/usuaris/usuarisForm";
	}  
    
    @PostMapping("/saveUsuaris")
	public String guardarUser(User user, RedirectAttributes attributes) {
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		// Hacemos un set al atributo password (ya viene encriptado)
		user.setPassword(pwdEncriptado);	
		user.setEstatus(1); // Activado por defecto
		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Role que le asignaremos al usuario nuevo
		Role role = new Role();
		role.setId(3); // Role USUARIO
		user.agregar(role);
	
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUser.guardar(user);
				  
		return "redirect:/usuaris/indexPaginate";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String UserUpdate(@PathVariable(value = "id") Integer id, Model model) {
		User user = serviceUser.getUserById(id);
		model.addAttribute("user", user);
		return "usuaris/usuarisUpdate";
	}
    
    @PostMapping("/showFormForUpdate/{id}")
    public String updateUser(@RequestParam("id") Integer id, User user){
    	
  		String pwdPlano = user.getPassword();
    	String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
    		// Hacemos un set al atributo password (ya viene encriptado)
    	user.setPassword(pwdEncriptado);	
   		user.setEstatus(1); // Activado por defecto
 		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor			
 			// Creamos el Role que le asignaremos al usuario nuevo
    	Role role = new Role();
 		role.setId(3); // Role USUARIO
 		user.agregar(role); 		
   			/**
    		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
    		 */
    		serviceUser.guardar(user);
    	
		return "redirect:/usuaris/indexPaginate";
	}
	

	@RequestMapping("/indexPaginate")
	public String vewListPaginate(Model model) {
		return listByPage(model, 1, "firstName", "asc");
	}

	@RequestMapping("/indexPaginate/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		List<Clase> listClases = serviceClase.listaClases();
		
		Page<User> page = serviceUser.findAll(currentPage, sortField, sortDir);

		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<User> lista = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listUser", lista);
		model.addAttribute("clase", listClases);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		return "usuaris/usuarisIndex";
	}


	
	/**
	 * Método para activar un usuario
	 * 
	 * @param idUsuario
	 * @param attributes
	 * @return
	 */
	@GetMapping("/unlock/{id}")
	public String activar(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		serviceUser.activar(idUser);
		attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");
		return "redirect:/usuaris/index";
	}

	/**
	 * Método para bloquear un usuario
	 * 
	 * @param idUsuario
	 * @param attributes
	 * @return
	 */
	@GetMapping("/lock/{id}")
	public String bloquear(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		serviceUser.bloquear(idUser);
		attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
		return "redirect:/usuaris/index";
	}
}
