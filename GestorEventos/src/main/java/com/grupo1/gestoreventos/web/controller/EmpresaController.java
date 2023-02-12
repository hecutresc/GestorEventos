package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.service.EmpresaService;

@Controller
public class EmpresaController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	// Objetos Autowired
	@Autowired
	private EmpresaService empresaService;

	// Métodos del controller
	@GetMapping("/admin/empresas")
	public ModelAndView findAll() {
		// Mostramos todos los caterings
		log.info("EmpresasController - findAll: Recoge todas las empresas");
		List<EmpresaDTO> listaEmpresasDTO = empresaService.findAll();

		// Mostramos los valores
		for (EmpresaDTO empresaDTO : listaEmpresasDTO) {
			System.out.println(empresaDTO.toString());
		}

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView();
		mav.addObject("listaEmpresasDTO", listaEmpresasDTO);

		return null;

	}

	@GetMapping()
	public ModelAndView delete(@PathVariable Long idEmpresa) {
		// Mostramos todos los caterings
		log.info("EmpresasController - delete: Elimina la empresa "+idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		
		//Llamamnos al service
		empresaService.delete(empresaDTO);
		
		//Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas");
		
		return null;
		
		
	}

}
