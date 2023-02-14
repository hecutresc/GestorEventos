package com.grupo1.gestoreventos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {
	
	@GetMapping("/login")
	public String login() {
		return "views/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "views/register";
	}

}
