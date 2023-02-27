package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/admin/usuarios")
	public ModelAndView findAll() {
		log.info("UsuarioController - findAll: Muestra la lista de todos los usuarios");

		List<UsuarioDTO> usuariosDTO = usuarioService.findAll();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/usuarios");
		mv.addObject("usuariosDTO", usuariosDTO);

		return mv;
	}

	@GetMapping("/admin/usuarios/add")
	public ModelAndView add() {
		log.info("UsuarioController - add: Muestra el formulario para nuevo usuario");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/usuarioform");
		mv.addObject("usuarioDTO", new UsuarioDTO());
		mv.addObject("add", true);

		return mv;
	}

	@GetMapping("/admin/usuarios/{idUsuario}/update")
	public ModelAndView update(@PathVariable("idUsuario") Long idUsuario) {
		log.info("UsuarioController - update: Muesta form actualizar para el usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		System.out.println(usuarioDTO.getDireccionDTO().toString());

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/usuarioform");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("add", false);

		return mv;
	}

	@PostMapping("/admin/usuarios/save")
	public ModelAndView save(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) {
		log.info("UsuarioController - save: Guarda el usuario: " + usuarioDTO.toString());

		/**
		 * VALIDACIÓN
		 */

		usuarioService.save(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/usuarios");

		return mv;
	}

	@GetMapping("/admin/usuarios/{idUsuario}/delete")
	public ModelAndView delete(@PathVariable("idUsuario") Long idUsuario) {
		log.info("InvitadoController - delete: Borra el Usuario con ID: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		usuarioService.delete(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/usuarios");

		return mv;
	}
	
	@GetMapping("/admin/usuarios/{idUsuario}")
	public ModelAndView findById(@PathVariable("idUsuario") Long idUsuario) {
		log.info("UsuarioController - findById: Muestra el usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/usuarioshow");
		mv.addObject("usuarioDTO", usuarioDTO);

		return mv;
	}

	//Métodos Register
	
	@GetMapping("/register")
	public ModelAndView register() {
		log.info("UsuarioController - register: Muestra el formulario de registro");
		ModelAndView mav = new ModelAndView("views/register");
		mav.addObject("usuarioDTO", new UsuarioDTO());
		// retornamos
		return mav;
	}

	@PostMapping("/register/save")
	public ModelAndView save2(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) {
		log.info("UsuarioController - save: Salvamos los datos del usuario:" + usuarioDTO.toString());
		
		// Invocamos a la capa de servicios para que almacene los datos del usuario
		usuarioService.save(usuarioDTO);
		// Redireccionamos para volver a invocar a la raiz
		ModelAndView mav = new ModelAndView("redirect:/login");
		return mav;
	}
}
