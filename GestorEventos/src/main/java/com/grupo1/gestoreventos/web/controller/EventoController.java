package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.EventoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	@GetMapping("/admin/usuarios/{idUsuario}/eventos")
	public ModelAndView findAllByUsuario(@PathVariable("idUsuario") Long idUsuario) {
		log.info("EventoController - findAllByUsuario: Muestra los eventos del usuario: "+idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		
		List<EventoDTO> eventosDTO = eventoService.findAllByUser(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/eventos");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventosDTO", eventosDTO);

		return mv;
	}
}
