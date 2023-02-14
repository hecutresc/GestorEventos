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

@Controller
@Slf4j
public class InvitadoController {

	@Autowired
	private InvitadoService invitadoService;

	@GetMapping("/admin/evento/{idevento}/invitados")
	public ModelAndView findAllByEvento(@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - findAllByEvento: Muestra la lista de invitados del Evento: " + idEvento);

		EventoDTO eventoDTO = new EventoDTO(idEvento);

		List<InvitadoDTO> listaInvitadosDTO = invitadoService.findAllByEvento(eventoDTO);

		for (InvitadoDTO invitadoDTO : listaInvitadosDTO) {
			System.out.println(invitadoDTO);
		}

		// ModelAndView mv = new ModelAndView();
		// mv.setViewName("invitados");
		// mv.addObject("eventoDTO", eventoDTO);
		// mv.addObject("listaInvitadosDTO", listaInvitadosDTO);

		return null;
	}

	@GetMapping("/admin/evento/{idevento}/invitados/add")
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

	@PostMapping("/admin/evento/{idevento}/invitados/save")
	public ModelAndView save(@ModelAttribute("invitadoDTO") InvitadoDTO invitadoDTO,
			@PathVariable("idEvento") Long idEvento) {
		log.info("InvitadoController - save: Guarda el invitado en el evento:" + idEvento);

		/**
		 *    VALIDACIÓN
		 */
		
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		invitadoDTO.setEventoDTO(eventoDTO);
		
		invitadoService.save(invitadoDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/evento/{idevento}/invitados");

		return null;
	}
}
