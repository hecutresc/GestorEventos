package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.grupo1.gestoreventos.service.DecoradoService;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.OcioService;
import com.grupo1.gestoreventos.service.UbicacionService;
import com.grupo1.gestoreventos.service.UsuarioService;

import jakarta.validation.Valid;

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
	@Autowired
	private DecoradoService decoradoService;
	@Autowired
	private OcioService ocioService;

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
	public ModelAndView findAllByUsuarioUser(@PathVariable("idUsuario") Long idUsuario,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("EventoController - findAllByUsuario: Muestra los eventos del usuario: " + idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		if (usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {

			List<EventoDTO> eventosDTO = eventoService.findAllByUser(usuarioDTO);

			ModelAndView mv = new ModelAndView();
			mv.setViewName("app/eventosuser");
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventosDTO", eventosDTO);

			return mv;
		} else {
			ModelAndView mav = new ModelAndView("errors/403");
			return mav;
		}

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
		mav.addObject("decoradoDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO());
		mav.addObject("ocioDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO());
		return mav;
	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}")
	public ModelAndView showEventos2(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		// Recogemos el evento
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		// ModelAndView
		ModelAndView mav = new ModelAndView("app/evento_info");
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("cateringDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO());
		mav.addObject("decoradoDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO());
		mav.addObject("ocioDTO", eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO());
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
		mav.addObject("listaDecoradosDTO", decoradoService.findAll());
		mav.addObject("listaOciosDTO", ocioService.findAll());
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", new EventoDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Alta de eventos desde usuario
	@GetMapping({ "/user/usuarios/{idUsuario}/eventos/add" })
	public ModelAndView addEvento(@PathVariable("idUsuario") Long idUsuario,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {

		log.info("EventoController - add: Anyadimos un nuevo evento " + idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		if (usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			ModelAndView mav = new ModelAndView("app/eventoformuser");
			mav.addObject("listaCateringsDTO", cateringService.findAll());
			mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
			mav.addObject("listaDecoradosDTO", decoradoService.findAll());
			mav.addObject("listaOciosDTO", ocioService.findAll());
			mav.addObject("usuarioDTO", usuarioDTO);
			mav.addObject("eventoDTO", new EventoDTO());
			mav.addObject("add", true);

			return mav;
		} else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}

	}

	// Actualizar la informacion de un evento desde admin
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/update/{idEvento}")
	public ModelAndView update(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento) {

		log.info("ClienteController - update: Modificamos el evento: " + idEvento);

		// Seteamos la empresa
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		// Obtenemos el evento y lo pasamos al modelo para ser actualizado
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);
		eventoDTO.setUsuarioDTO(usuarioDTO);
		eventoDTO.setUbicacionDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getUbicacionDTO());
		eventoDTO.setCateringDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO());
		eventoDTO.setDecoradoDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO());
		eventoDTO.setOcioDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO());
		eventoDTO.getUsuarioDTO().getListaRolesDTO().get(0).setUsuarioDTO(null);
		eventoDTO.setListaCateringubicacioneventoDTO(null);
		ModelAndView mav = new ModelAndView("app/eventoform2");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("eventoDTO", eventoDTO);
		mav.addObject("listaCateringsDTO", cateringService.findAll());
		mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
		mav.addObject("listaDecoradosDTO", decoradoService.findAll());
		mav.addObject("listaOciosDTO", ocioService.findAll());
		mav.addObject("add", false);

		return mav;
	}

	// Actualizar la informacion de un evento desde usuarios
	@GetMapping("/user/usuarios/{idUsuario}/eventos/update/{idEvento}")
	public ModelAndView updateUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {

		log.info("ClienteController - update: Modificamos el evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		if (usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			// Obtenemos el evento y lo pasamos al modelo para ser actualizado
			EventoDTO eventoDTO = new EventoDTO();
			eventoDTO.setId(idEvento);
			eventoDTO = eventoService.findById(eventoDTO);
			eventoDTO.setUsuarioDTO(usuarioDTO);
			eventoDTO.setUbicacionDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getUbicacionDTO());
			eventoDTO.setCateringDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO());
			eventoDTO.setDecoradoDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO());
			eventoDTO.setOcioDTO(eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO());
			eventoDTO.getUsuarioDTO().getListaRolesDTO().get(0).setUsuarioDTO(null);
			eventoDTO.setListaCateringubicacioneventoDTO(null);
			ModelAndView mav = new ModelAndView("app/eventoformuser2");
			mav.addObject("usuarioDTO", usuarioDTO);
			mav.addObject("eventoDTO", eventoDTO);
			mav.addObject("listaCateringsDTO", cateringService.findAll());
			mav.addObject("listaUbicacionesDTO", ubicacionService.findAll());
			mav.addObject("listaDecoradosDTO", decoradoService.findAll());
			mav.addObject("listaOciosDTO", ocioService.findAll());
			mav.addObject("add", false);

			return mav;
		} else {
			ModelAndView mav = new ModelAndView("errors/403");
			return mav;
		}

	}

	// Salvar eventos
	@PostMapping("/admin/usuarios/{idUsuario}/eventos/save")
	public ModelAndView save(@PathVariable("idUsuario") Long idUsuario,
			@ModelAttribute("eventoDTO") @Valid EventoDTO eventoDTO, BindingResult bindingResult) {

		log.info("ClienteController - save: Salvamos los datos del evento:" + eventoDTO.toString());

		// Seteamos la empresa al nuevo Catering
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		// Comprobamos que todos los datos estan correctos
		if (bindingResult.hasErrors() == true) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventoDTO", eventoDTO);
			mv.addObject("listaCateringsDTO", cateringService.findAll());
			mv.addObject("listaUbicacionesDTO", ubicacionService.findAll());
			mv.addObject("listaDecoradosDTO", decoradoService.findAll());
			mv.addObject("listaOciosDTO", ocioService.findAll());

			if (eventoDTO.getId() != null) {
				// Editar
				mv.addObject("add", false);
				mv.setViewName("app/eventoform2");
				return mv;
			} else {
				// añadir
				mv.addObject("add", true);
				mv.setViewName("app/eventoform");
				return mv;
			}
		}

		eventoDTO.setUsuarioDTO(usuarioDTO);

		eventoService.save(eventoDTO);

		ModelAndView mav = new ModelAndView("redirect:/admin/usuarios/{idUsuario}/eventos");

		return mav;
	}

	// Salvar eventos
	@PostMapping("/user/usuarios/{idUsuario}/eventos/save")
	public ModelAndView saveUser(@PathVariable("idUsuario") Long idUsuario,
			@ModelAttribute("eventoDTO") EventoDTO eventoDTO,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {

		log.info("ClienteController - save: Salvamos los datos del evento:" + eventoDTO.toString());
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);

		if (usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			eventoDTO.setUsuarioDTO(usuarioDTO);

			eventoService.save(eventoDTO);

			// Redireccionamos para volver a invocar el metodo que escucha /eventos
			ModelAndView mav = new ModelAndView("redirect:/user/usuarios/{idUsuario}/eventos");

			return mav;
		} else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}

	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/delete/{idEvento}")
	public ModelAndView deleteUser(@PathVariable("idUsuario") Long idUsuario, @PathVariable Long idEvento,
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		// Eliminamos el evento
		log.info("EventoController - delete: Elimina el evento " + idEvento);
		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		if (usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			EventoDTO eventoDTO = new EventoDTO();
			eventoDTO.setId(idEvento);

			// Llamamos al service
			eventoService.delete(eventoDTO);

			// Volvemos a la vista principal
			ModelAndView mav = new ModelAndView("redirect:/user/usuarios/{idUsuario}/eventos");

			return mav;
		} else {
			ModelAndView mav = new ModelAndView("errors/503");
			return mav;
		}

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
