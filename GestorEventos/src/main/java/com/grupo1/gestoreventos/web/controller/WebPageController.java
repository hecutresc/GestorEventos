package com.grupo1.gestoreventos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.UsuarioService;

@Controller
public class WebPageController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/admin")
	public String admin() {
		return "app/admin";
	}
	
	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView mav = new ModelAndView("app/user");
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(5L);
		
		mav.addObject("usuarioDTO",usuarioService.findById(usuarioDTO));
		
		return mav;
		
	
	}
	
	@GetMapping("/login")
	public String login() {
		return "views/login";
	}

	@GetMapping("/register")
	public String register() {
		return "views/register";
	}

	@GetMapping("/caterings")
	public String caterings() {
		return "app/caterings";
	}

	@GetMapping("/direcciones")
	public String direcciones() {
		return "app/direcciones";
	}

	@GetMapping("/empresas")
	public String empresas() {
		return "app/empresas";
	}

	@GetMapping("/eventos")
	public String eventos() {
		return "app/eventos";
	}

	@GetMapping("/ubicaciones")
	public String ubicaciones() {
		return "app/ubicaciones";
	}

	@GetMapping("/usuarios")
	public String usuarios() {
		return "app/usuarios";
	}

}
