package com.grupo1.gestoreventos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

	@GetMapping("/admin")
	public String admin() {
		return "app/admin";
	}
	
	@GetMapping("/user")
	public String user() {
		return "app/user";
	}
	
	/*@GetMapping("/login")
	public String login() {
		return "views/login";
	}*/

	/*@GetMapping("/register")
	public String register() {
		return "views/register";
	}*/

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
