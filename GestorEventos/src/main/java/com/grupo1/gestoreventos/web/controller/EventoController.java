package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.service.EventoService;

@Controller
public class EventoController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	// Objetos Autowired
	@Autowired
	private EventoService eventoService;

	@GetMapping("/admin/usuarios/{idUsuario}/eventos")
	public ModelAndView findByUsuario(@PathVariable("idUsuario") Long idUsuario) {
		// Mostramos todos los eventos
		log.info("EventoController - findByEmpresa: Recoge todos los eventos del usuario " + idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		List<EventoDTO> listaEventosDTO = eventoService.findAllByUsuario(usuarioDTO);

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView("app/eventos");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("listaEventosDTO", listaEventosDTO);

		return mav;

	}

	// Alta de eventos
	@GetMapping({"/admin/usuarios/{idUsuario}/eventos/add","/menu/usuarios/{idUsuario}{eventos}/add"})
	public ModelAndView add(@PathVariable("idUsuario") Long idUsuario) {

		log.info("EventoController - add: Anyadimos un nuevo evento " + idUsuario);

		ModelAndView mav = new ModelAndView("app/eventoform");
		mav.addObject("eventoDTO", new EventoDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Actualizar la informacion de un evento
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/update/{idEvento}")
	public ModelAndView update(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {

		log.info("ClienteController - update: Modificamos el evento: " + idEvento);

		// Seteamos la empresa
		EmpresaDTO usuarioDTO = new EmpresaDTO();
		usuarioDTO.setId(idUsuario);

		// Obtenemos el evento y lo pasamos al modelo para ser actualizado
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		ModelAndView mav = new ModelAndView("eventoform");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Salvar eventos
	@PostMapping("/admin/usuarios/{idUsuario}/eventos/save")
	public ModelAndView save(@PathVariable("idUsuario") Long idUsuario, @ModelAttribute("eventoDTO") EventoDTO eventoDTO) {

		log.info("ClienteController - save: Salvamos los datos del evento:" + eventoDTO.toString());
		
		//Seteamos la empresa al nuevo Catering
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		
		// Invocamos a la capa de servicios para que almacene los datos del evento
		eventoService.save(eventoDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /eventos
		ModelAndView mav = new ModelAndView("redirect:/admin/usuarios/{idUsuario}/eventos");
		return mav;

	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/delete/{idEvento}")
	public ModelAndView delete(@PathVariable("idUsuario") Long idUsuario, @PathVariable Long idEvento) {
		// Eliminamos el evento
		log.info("EventoController - delete: Elimina el evento " + idEvento);
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);

		// Llamamos al service
		eventoService.delete(eventoDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/usuarios/{idUsuario}/eventos");

		return mav;

	}

}
