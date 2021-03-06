package com.app.dracmagicv6.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dracmagicv6.model.Alumno;
import com.app.dracmagicv6.model.Clase;
import com.app.dracmagicv6.model.Role;
import com.app.dracmagicv6.model.User;
import com.app.dracmagicv6.service.AlumnoService;
import com.app.dracmagicv6.service.ClaseService;
import com.app.dracmagicv6.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AlumnoService serviceAlumno;
	
	@Autowired
	private ClaseService serviceClase;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}

	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("user") == null){
			User user = service.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("user", user);
		}
		
		return "redirect:/";
	}
	
	/**
	 * Método que muestra el formulario para que se registren nuevos usuarios.
	 * @param usuario
	 * @return
	 */
	@GetMapping("/signup")
	public String registrarse(User user) {
		return "formRegistro";
	}
	
	/**
	 * Método que guarda en la base de datos el usuario registrado
	 * @param usuario
	 * @param attributes
	 * @return
	 */
	@PostMapping("/signup")
	public String guardarRegistro(User user, RedirectAttributes attributes) {
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
		service.guardar(user);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
	
	/**
	 * Método que muestra el formulario de login personalizado.
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	
    
    //Atención averiguar por que solo me funciona en HomeController
    @GetMapping("/saveAlumnes")
	public String mostrarForm(Alumno alumno) {
		return "alumnes/alumnesForm";
	}
    
   
    
    //Atención averiguar por que solo me funciona en HomeController
    @GetMapping("/saveClases")
	public String mostrarForm(Clase clase) {
		return "gestio/clasesForm";
	}
    
  //Atención averiguar por que solo me funciona en HomeController
    @PostMapping("/saveClases")
    public String guardarClase(Clase clase, RedirectAttributes attributes) {
		serviceClase.guardar(clase);
		return "redirect:/alumnes/indexPaginateAlumnos";
	}
    
    //Atención averiguar por que solo me funciona en HomeController
    @GetMapping("/saveRoles")
	public String mostrarForm(Role Role) {
		return "gestio/rolesForm";
	}

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value= "id") Integer id, Model model) {
    	this.service.deleteUserById(id);
    	return "redirect:/usuaris/indexPaginate";
    }
    
    @GetMapping("/showFormForUpdateAlumno/{id}")
	public String AlumnoUpdate(@PathVariable(value = "id") Integer id, Model model) {
		Alumno alumno = serviceAlumno.getAlumnoById(id);
		model.addAttribute("alumno", alumno);
		return "alumnes/alumnnesUpdate";
    }
    
    @PostMapping("/showFormForUpdateAlumno/{id}")
    public String updateAlumno(@RequestParam("id") Integer id, Alumno alumno){
  		
    		serviceAlumno.guardar(alumno);
    	
		return "redirect:/alumnes/indexPaginate";
	}
    
	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
}
