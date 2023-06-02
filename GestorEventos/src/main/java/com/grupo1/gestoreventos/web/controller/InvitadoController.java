package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.InvitadoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.InvitadoService;
import com.grupo1.gestoreventos.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InvitadoController {

	@Autowired
	private InvitadoService invitadoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados")
	public ModelAndView findAllByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - findAllByEvento: Muestra la lista de invitados del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);

		List<InvitadoDTO> listaInvitadosDTO = invitadoService.findAllByEvento(eventoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/invitados");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("listaInvitadosDTO", listaInvitadosDTO);

		return mv;
	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados")
	public ModelAndView findAllByEventoUser(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("InvitadoController - findAllByEvento: Muestra la lista de invitados del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			EventoDTO eventoDTO = new EventoDTO(idEvento);

			List<InvitadoDTO> listaInvitadosDTO = invitadoService.findAllByEvento(eventoDTO);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("app/invitadosuser");
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventoDTO", eventoDTO);
			mv.addObject("listaInvitadosDTO", listaInvitadosDTO);

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/403");
			return mav;
		}
		
	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados/add")
	public ModelAndView addUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("InvitadoController - add: Muestra el formulario para nuevo invitado");
		
			UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
			usuarioDTO = usuarioService.findById(usuarioDTO);
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			EventoDTO eventoDTO = new EventoDTO(idEvento);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("app/invitadoformuser");
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventoDTO", eventoDTO);
			mv.addObject("invitadoDTO", new InvitadoDTO());
			mv.addObject("add", true);

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}
		
	}
	
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados/add")
	public ModelAndView add(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - add: Muestra el formulario para nuevo invitado");

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/invitadoform");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("invitadoDTO", new InvitadoDTO());
		mv.addObject("add", true);

		return mv;
	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados/{idInvitado}/update")
	public ModelAndView update(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento,
			@PathVariable("idInvitado") Long idInvitado) {
		log.info("InvitadoController - update: Muesta form actualizar para el invitado: " + idInvitado);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);

		InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);
		invitadoDTO = invitadoService.findById(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/invitadoform");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("invitadoDTO", invitadoDTO);
		mv.addObject("add", false);

		return mv;
	}
	
	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados/{idInvitado}/update")
	public ModelAndView updateUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento,
			@PathVariable("idInvitado") Long idInvitado, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("InvitadoController - update: Muesta form actualizar para el invitado: " + idInvitado);
			UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
			usuarioDTO = usuarioService.findById(usuarioDTO);
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			EventoDTO eventoDTO = new EventoDTO(idEvento);

			InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);
			invitadoDTO = invitadoService.findById(invitadoDTO);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("app/invitadoformuser");
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventoDTO", eventoDTO);
			mv.addObject("invitadoDTO", invitadoDTO);
			mv.addObject("add", false);

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}
		
	}

	@PostMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados/save")
	public ModelAndView save(@Valid @ModelAttribute("invitadoDTO") InvitadoDTO invitadoDTO, BindingResult bindingResult,
			@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - save: Guarda el invitado en el evento:" + idEvento);

		/**
		 * VALIDACIÓN
		 */
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		invitadoDTO.setEventoDTO(eventoDTO);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		
		if(bindingResult.hasErrors() == true) {
			if(invitadoDTO.getId() == null) {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("app/invitadoform");
				mv.addObject("usuarioDTO", usuarioDTO);
				mv.addObject("eventoDTO", eventoDTO);
				mv.addObject("invitadoDTO", invitadoDTO);
				mv.addObject("add", true);

				return mv;
			}else {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("app/invitadoform");
				mv.addObject("usuarioDTO", usuarioDTO);
				mv.addObject("eventoDTO", eventoDTO);
				mv.addObject("invitadoDTO", invitadoDTO);
				mv.addObject("add", false);

				return mv;
			}
		}

		

		invitadoService.save(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados");

		return mv;
	}
	
	@PostMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados/save")
	public ModelAndView saveUser(@Valid @ModelAttribute("invitadoDTO") InvitadoDTO invitadoDTO,BindingResult bindingResult,
			@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("InvitadoController - save: Guarda el invitado en el evento:" + idEvento);
			/**
			 * VALIDACIÓN
			 */

			EventoDTO eventoDTO = new EventoDTO(idEvento);
			invitadoDTO.setEventoDTO(eventoDTO);
			UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
			usuarioDTO = usuarioService.findById(usuarioDTO);
			
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			
			if(bindingResult.hasErrors() == true) {
				if(invitadoDTO.getId() == null) {
					ModelAndView mv = new ModelAndView();
					mv.setViewName("app/invitadoformuser");
					mv.addObject("usuarioDTO", usuarioDTO);
					mv.addObject("eventoDTO", eventoDTO);
					mv.addObject("invitadoDTO", invitadoDTO);
					mv.addObject("add", true);

					return mv;
				}else {
					ModelAndView mv = new ModelAndView();
					mv.setViewName("app/invitadoformuser");
					mv.addObject("usuarioDTO", usuarioDTO);
					mv.addObject("eventoDTO", eventoDTO);
					mv.addObject("invitadoDTO", invitadoDTO);
					mv.addObject("add", false);

					return mv;
				}
			}

			invitadoService.save(invitadoDTO);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados");

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}
		
	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados/{idInvitado}/delete")
	public ModelAndView deleteUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento,
			@PathVariable("idInvitado") Long idInvitado, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("InvitadoController - delete: Borra el invitado con ID: " + idInvitado);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);

			invitadoService.deleteById(invitadoDTO);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/user/usuarios/{idUsuario}/eventos/{idEvento}/invitados");

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}
		
	}
	
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados/{idInvitado}/delete")
	public ModelAndView delete(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento,
			@PathVariable("idInvitado") Long idInvitado) {
		log.info("InvitadoController - delete: Borra el invitado con ID: " + idInvitado);

		InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);

		invitadoService.deleteById(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/usuarios/{idUsuario}/eventos/{idEvento}/invitados");

		return mv;
	}
}
