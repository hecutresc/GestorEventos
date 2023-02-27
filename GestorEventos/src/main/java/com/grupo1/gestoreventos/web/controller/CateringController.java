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
import com.grupo1.gestoreventos.repository.entity.Empresa;
import com.grupo1.gestoreventos.service.CateringService;
import com.grupo1.gestoreventos.service.EventoService;

@Controller
public class CateringController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(CateringController.class);

	// Objetos Autowired
	@Autowired
	private CateringService cateringService;
	
	@Autowired
	private EventoService eventoService;

	@GetMapping("/admin/empresas/{idEmpresa}/caterings")
	public ModelAndView findByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
		// Mostramos todos los caterings
		log.info("CateringController - findByEmpresa: Recoge todos los caterings de la empresa " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		List<CateringDTO> listaCateringsDTO = cateringService.findAllByEmpresa(empresaDTO);

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView("/app/caterings");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("listaCateringsDTO", listaCateringsDTO);

		return mav;

	}

	// Alta de clientes
	@GetMapping("/admin/empresas/{idEmpresa}/caterings/add")
	public ModelAndView add(@PathVariable("idEmpresa") Long idEmpresa) {

		log.info("CateringController - add: Anyadimos un nuevo cliente " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		ModelAndView mav = new ModelAndView("app/cateringform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("cateringDTO", new CateringDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Actualizar la informacion de un cliente
	@GetMapping("/admin/empresas/{idEmpresa}/caterings/update/{idCatering}")
	public ModelAndView update(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idCatering") Long idCatering) {

		log.info("ClienteController - update: Modificamos el cliente: " + idCatering);

		// Seteamos la empresa
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		CateringDTO cateringDTO = new CateringDTO();
		cateringDTO.setId(idCatering);
		cateringDTO = cateringService.findById(cateringDTO);

		ModelAndView mav = new ModelAndView("app/cateringform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("cateringDTO", cateringDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Salvar clientes
	@PostMapping("/admin/empresas/{idEmpresa}/caterings/save")
	public ModelAndView save(@PathVariable("idEmpresa") Long idEmpresa, @ModelAttribute("cateringDTO") CateringDTO cateringDTO) {

		log.info("ClienteController - save: Salvamos los datos del cliente:" + cateringDTO.toString());
		
		//Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		cateringDTO.setEmpresaDTO(empresaDTO);
		
		// Invocamos a la capa de servicios para que almacene los datos del cliente
		cateringService.save(cateringDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/caterings");
		return mav;

	}

	@GetMapping("/admin/empresas/{idEmpresa}/caterings/delete/{idCatering}")
	public ModelAndView delete(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable Long idCatering) {
		// Eliminamos el catering
		log.info("CateringController - delete: Elimina el catering " + idCatering);
		CateringDTO cateringDTO = new CateringDTO();
		cateringDTO.setId(idCatering);

		// Llamamos al service
		cateringService.delete(cateringDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/caterings");

		return mav;

	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/catering")
	public ModelAndView findByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("CateringController - findByEvento: Muestra el Catering del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		CateringDTO cateringDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/cateringshow");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("cateringDTO", cateringDTO);

		return mv;

	}
	
	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/catering")
	public ModelAndView findByEventoUser(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("CateringController - findByEvento: Muestra el Catering del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		CateringDTO cateringDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getCateringDTO();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/cateringshowuser");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("cateringDTO", cateringDTO);

		return mv;

	}
	
}
