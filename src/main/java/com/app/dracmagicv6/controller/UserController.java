package com.app.dracmagicv6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dracmagicv6.model.User;
import com.app.dracmagicv6.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService serviceUser;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	 @GetMapping("/index")
	 public String viewList(Model model) {
	    List<User> lista = serviceUser.buscarTodos();
	   	model.addAttribute( "listUser" , lista);
	   	return "usuaris/usuarisIndex";
	 }
	
	
    @GetMapping(value= "/indexPaginate")
    public String viewListPaginate(Model model, Pageable page) {
    	Page<User> lista = serviceUser.buscarTodos(page);
    	model.addAttribute( "listUser" , lista);
    	return "usuaris/usuarisIndex";
    }
    
    
    
//    public String usuarisForm(Model model) {
//    	User user = new User();
//    	model.addAttribute("user", user);
//    	return "new_user";
//    }
//    
    
    
    
//    List<User> buscarTodos();
//    
//    @Override
//	public List<User> buscarTodos() {
//		return userRepo.findAll();
//	}
    
    /**
     * Método para eliminar un usuario de la base de datos.
     * @param idUsuario
     * @param attributes
     * @return
     */
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		    	
		// Eliminamos el usuario
    	serviceUser.eliminar(idUser);
			
		attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
		return "redirect:/user/index";
    }
    
    /**
     * Método para activar un usuario
     * @param idUsuario
     * @param attributes
     * @return
     */
    @GetMapping("/unlock/{id}")
	public String activar(@PathVariable("id") int idUser, RedirectAttributes attributes) {		
    	serviceUser.activar(idUser);
		attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");		
		return "redirect:/user/index";
	}
    
	/**
	 * Método para bloquear un usuario
	 * @param idUsuario
	 * @param attributes
	 * @return
	 */
	@GetMapping("/lock/{id}")
	public String bloquear(@PathVariable("id") int idUser, RedirectAttributes attributes) {		
		serviceUser.bloquear(idUser);
		attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");		
		return "redirect:/user/index";
	}
}
