package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.InvitadoDTO;
import com.grupo1.gestoreventos.service.InvitadoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InvitadoController {

	@Autowired
	private InvitadoService invitadoService;

	@GetMapping("/admin/eventos/{idEvento}/invitados")
	public ModelAndView findAllByEvento(@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - findAllByEvento: Muestra la lista de invitados del Evento: " + idEvento);

		EventoDTO eventoDTO = new EventoDTO(idEvento);

		List<InvitadoDTO> listaInvitadosDTO = invitadoService.findAllByEvento(eventoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/invitados");
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("listaInvitadosDTO", listaInvitadosDTO);

		return mv;
	}

	@GetMapping("/admin/eventos/{idevento}/invitados/add")
	public ModelAndView add(@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - add: Muestra el formulario para nuevo invitado");

		EventoDTO eventoDTO = new EventoDTO(idEvento);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("invitadoform");
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("invitadoDTO", new InvitadoDTO());
		mv.addObject("add", true);

		return null;
	}

	@GetMapping("/admin/eventos/{idEvento}/invitados/{idInvitado}/update")
	public ModelAndView update(@PathVariable("idEvento") Long idEvento, @PathVariable("idInvitado") Long idInvitado) {
		log.info("InvitadoController - update: Muesta form actualizar para el invitado: " + idInvitado);

		EventoDTO eventoDTO = new EventoDTO(idEvento);

		InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);
		invitadoDTO = invitadoService.findById(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("invitadoform");
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("invitadoDTO", invitadoDTO);
		mv.addObject("add", false);

		System.out.println(invitadoDTO.toString());
		return null;
	}

	@PostMapping("/admin/eventos/{idEvento}/invitados/save")
	public ModelAndView save(@ModelAttribute("invitadoDTO") InvitadoDTO invitadoDTO,
			@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - save: Guarda el invitado en el evento:" + idEvento);

		/**
		 * VALIDACIÓN
		 */

		EventoDTO eventoDTO = new EventoDTO(idEvento);
		invitadoDTO.setEventoDTO(eventoDTO);

		invitadoService.save(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/evento/{idevento}/invitados");

		return mv;
	}

	@GetMapping("/admin/eventos/{idEvento}/invitados/{idInvitado}/delete")
	public ModelAndView delete(@PathVariable("idEvento") Long idEvento, @PathVariable("idInvitado") Long idInvitado) {
		log.info("InvitadoController - delete: Boora el usuario con ID: " + idInvitado);

		InvitadoDTO invitadoDTO = new InvitadoDTO(idInvitado);

		// invitadoService.deleteById(invitadoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/evento/{idevento}/invitados");

		System.out.println(invitadoDTO.toString());

		return null;
	}
}
