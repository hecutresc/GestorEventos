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
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.CateringUbicacionEventoDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.service.CateringService;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.UbicacionService;
import com.grupo1.gestoreventos.service.UsuarioService;

@Controller
public class EventoController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	// Objetos Autowired
	@Autowired
	private EventoService eventoService;
	@Autowired
	private UbicacionService ubicacionService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CateringService cateringService;

	@GetMapping("/admin/usuarios/{idUsuario}/eventos")
	public ModelAndView findAllByUsuario(@PathVariable("idUsuario") Long idUsuario) {
		log.info("EventoController - findAllByUsuario: Muestra los eventos del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		List<EventoDTO> eventosDTO = eventoService.findAllByUser(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/eventos");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventosDTO", eventosDTO);

		return mv;
	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos")
	public ModelAndView findAllByUsuarioUser(@PathVariable("idUsuario") Long idUsuario) {
		log.info("EventoController - findAllByUsuario: Muestra los eventos del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		List<EventoDTO> eventosDTO = eventoService.findAllByUser(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/eventosuser");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventosDTO", eventosDTO);

		return mv;
	}

	@GetMapping("/admin/eventos")
	public ModelAndView findAll() {

		// Mostramos todos los eventos
		log.info("EventoController - findByEmpresa: Recoge todos los eventos");
		List<EventoDTO> listaEventosDTO = eventoService.findAll();

		// Mostramos la lista a la vista | implementar en un nuevo html
		ModelAndView mav = new ModelAndView("app/alleventos");
		mav.addObject("eventosDTO", listaEventosDTO);

		return mav;

	}

	@GetMapping("/admin/eventos/{idEvento}/show")
	public ModelAndView showEventos(@PathVariable("idEvento") Long idEvento) {

		// Recogemos el evento
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView("app/eventoshow");
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("cateringDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO());
		return mav;
	}

	// Alta de eventos desde administrador
	@GetMapping({ "/admin/usuarios/{idUsuario}/eventos/add" })
	public ModelAndView add(@PathVariable("idUsuario") Long idUsuario) {

		log.info("EventoController - add: Anyadimos un nuevo evento " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		ModelAndView mav = new ModelAndView("app/eventoform");
		mav.addObject("listaCateringsDTO", cateringService.findAll());
		mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", new EventoDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Alta de eventos desde usuario
	@GetMapping({ "/user/usuarios/{idUsuario}/eventos/add" })
	public ModelAndView addEvento(@PathVariable("idUsuario") Long idUsuario) {

		log.info("EventoController - add: Anyadimos un nuevo evento " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		ModelAndView mav = new ModelAndView("app/eventoformuser");
		mav.addObject("listaCateringsDTO", cateringService.findAll());
		mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", new EventoDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Actualizar la informacion de un evento desde admin
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/update/{idEvento}")
	public ModelAndView update(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {

		log.info("ClienteController - update: Modificamos el evento: " + idEvento);

		// Seteamos la empresa
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		// Obtenemos el evento y lo pasamos al modelo para ser actualizado
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		eventoDTO.getCateringDTO()
				.setId(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO().getId());

		ModelAndView mav = new ModelAndView("app/eventoform");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("listaCateringsDTO", cateringService.findAll());
		mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
		mav.addObject("add", false);

		return mav;
	}

	// Actualizar la informacion de un evento desde usuarios
	@GetMapping("/user/usuarios/{idUsuario}/eventos/update/{idEvento}")
	public ModelAndView updateUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {

		log.info("ClienteController - update: Modificamos el evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		// Obtenemos el evento y lo pasamos al modelo para ser actualizado
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);
		
		eventoDTO.getCateringDTO()
		.setId(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO().getId());

		ModelAndView mav = new ModelAndView("app/eventoformuser");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("listaCateringsDTO", cateringService.findAll());
		mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
		mav.addObject("add", false);

		return mav;
	}

	// Salvar eventos
	@PostMapping("/admin/usuarios/{idUsuario}/eventos/save")
	public ModelAndView save(@PathVariable("idUsuario") Long idUsuario,
			@ModelAttribute("eventoDTO") EventoDTO eventoDTO) {

		log.info("ClienteController - save: Salvamos los datos del evento:" + eventoDTO.toString());

		// Seteamos la empresa al nuevo Catering
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		eventoDTO.setUsuarioDTO(usuarioDTO);


		eventoService.save(eventoDTO);

		ModelAndView mav = new ModelAndView("redirect:/admin/usuarios/{idUsuario}/eventos");

		return mav;
	}

	// Salvar eventos
	@PostMapping("/user/usuarios/{idUsuario}/eventos/save")
	public ModelAndView saveUser(@PathVariable("idUsuario") Long idUsuario,
			@ModelAttribute("eventoDTO") EventoDTO eventoDTO) {

		log.info("ClienteController - save: Salvamos los datos del evento:" + eventoDTO.toString());

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		eventoDTO.setUsuarioDTO(usuarioDTO);

		eventoService.save(eventoDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /eventos
		ModelAndView mav = new ModelAndView("redirect:/user/usuarios/{idUsuario}/eventos");

		return mav;

	}
	
	@GetMapping("/user/usuarios/{idUsuario}/eventos/delete/{idEvento}")
	public ModelAndView deleteUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable Long idEvento) {
		// Eliminamos el evento
		log.info("EventoController - delete: Elimina el evento " + idEvento);
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);

		// Llamamos al service
		eventoService.delete(eventoDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/user/usuarios/{idUsuario}/eventos");

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

	@GetMapping("/admin/eventos/delete/{idEvento}")
	public ModelAndView delete(@PathVariable Long idEvento) {
		// Eliminamos el evento
		log.info("EventoController - delete: Elimina el evento " + idEvento);
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);

		// Llamamos al service
		eventoService.delete(eventoDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/eventos");

		return mav;

	}

}
