package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.OcioDTO;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.OcioService;

@Controller
public class OcioController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(OcioController.class);

	// Objetos Autowired
	@Autowired
	private OcioService ocioService;
	
	@Autowired
	private EventoService eventoService;

	// Métodos del controlador

	@GetMapping("/admin/empresas/{idEmpresa}/ocios")
	public ModelAndView findByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
		// Mostramos todos los caterings
		log.info("OcioController - findByEmpresa: Recoge todos los ocios disponibles de la empresa " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		List<OcioDTO> listaOcioDTO = ocioService.findAllByEmpresa(empresaDTO);

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView("/app/ocios");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("listaOciosDTO", listaOcioDTO);

		return mav;
	}
}
