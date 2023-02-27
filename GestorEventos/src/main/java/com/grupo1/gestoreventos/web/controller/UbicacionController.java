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

import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.UbicacionService;

@Controller
public class UbicacionController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(UbicacionController.class);

	// Objetos Autowired
	@Autowired
	private UbicacionService ubicacionService;
	
	@Autowired
	private EventoService eventoService;

	// Métodos
	@GetMapping("/admin/ubicaciones")
	public ModelAndView findAll() {
		// Mostramos todas las Ubicaciones
		log.info("UbicacionController - findAll: Recoge todas las ubicaciones");
		List<UbicacionDTO> listaUbicacionesDTO = ubicacionService.findAll();

		// Mostramos la lista
		ModelAndView mav = new ModelAndView("/app/ubicaciones");
		mav.addObject("listaUbicacionesDTO", listaUbicacionesDTO);
		return mav;

	}

	// Alta de clientes
	@GetMapping("/admin/ubicaciones/add")
	public ModelAndView add() {

		log.info("UbicacionesController - add: Anyadimos una nueva ubicacion ");

		ModelAndView mav = new ModelAndView("app/ubicacionform");
		mav.addObject("ubicacionDTO", new UbicacionDTO());
		mav.addObject("add", true);
		return mav;
	}

	// Actualizar la informacion de un cliente
	@GetMapping("/admin/ubicaciones/update/{idUbicacion}")
	public ModelAndView update(@PathVariable("idUbicacion") Long idUbicacion) {

		log.info("UbicacionController - update: Modificamos la ubicación: " + idUbicacion);
		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);
		ubicacionDTO = ubicacionService.findById(ubicacionDTO);

		ModelAndView mav = new ModelAndView("app/ubicacionform");
		mav.addObject("ubicacionDTO", ubicacionDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Salvar clientes
	@PostMapping("/admin/ubicaciones/save")
	public ModelAndView save(@ModelAttribute("ubicacionDTO") UbicacionDTO ubicacionDTO) {

		log.info("UbicacionController - save: Salvamos los datos de la ubicaion:" + ubicacionDTO.toString());

		// Invocamos a la capa de servicios para que almacene los datos del cliente
		ubicacionService.save(ubicacionDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/ubicaciones");
		return mav;

	}

	@GetMapping("/admin/ubicaciones/delete/{idUbicacion}")
	public ModelAndView delete(@PathVariable Long idUbicacion) {
		// Eliminamos la ubicación
		log.info("UbicacionController - delete: Elimina la ubicación " + idUbicacion);
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		// Llamamos al service
		ubicacionService.delete(ubicacionDTO);

		// Volvemos a la lista
		ModelAndView mav = new ModelAndView("redirect:/admin/ubicaciones");

		return mav;

	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/ubicacion")
	public ModelAndView findByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("UbicacionController - findByEvento: Muestra la Ubicacion del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		UbicacionDTO ubicacionDTO = ubicacionService.findById(eventoDTO.getUbicacionDTO());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ubicacionshow");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("ubicacionDTO", ubicacionDTO);

		return mv;

	}
}
