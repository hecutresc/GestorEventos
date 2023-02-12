package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/invitados")
	public ModelAndView findAllByEvento() {
		log.info("InvitadoController - findAllByEvento: Muestra la lista de invitados del Evento: " );

		EventoDTO eventoDTO = new EventoDTO(1L);
		
		List<InvitadoDTO> listaInvitadosDTO = invitadoService.findAllByEvento(eventoDTO); 
		
		
		
		for (InvitadoDTO invitadoDTO : listaInvitadosDTO) {
			System.out.println(invitadoDTO);
		}
		
		
		
		//ModelAndView mv = new ModelAndView();
		//mv.setViewName("invitados");
		//mv.addObject("eventoDTO", eventoDTO);
		//mv.addObject("listaInvitadosDTO", listaInvitadosDTO);

		return null;
	}

}
