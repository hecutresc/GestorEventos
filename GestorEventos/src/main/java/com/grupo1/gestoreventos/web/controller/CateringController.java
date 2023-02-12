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
import com.grupo1.gestoreventos.service.CateringService;

@Controller
public class CateringController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(CateringController.class);

	// Objetos Autowired
	@Autowired
	private CateringService cateringService;

	@GetMapping("/admin/caterings")
	public ModelAndView findAll() {
		// Mostramos todos los caterings
		log.info("CateringController - findAll: Recoge todos los caterings");
		List<CateringDTO> listaCateringsDTO = cateringService.findAll();

		// Mostramos los valores por consola
		for (CateringDTO c : listaCateringsDTO) {
			System.out.println(c.toString());
		}

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView();
		mav.addObject("listaCateringsDTO", listaCateringsDTO);

		return null;

	}

	@GetMapping("/admin/caterings/delete/{idCatering}")
	public ModelAndView delete(@PathVariable Long idCatering) {
		// Eliminamos el catering
		log.info("CateringController - delete: Elimina el catering " + idCatering);
		CateringDTO cateringDTO = new CateringDTO();
		cateringDTO.setId(idCatering);
		
		//Llamamos al service
		cateringService.delete(cateringDTO);
		
		//Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/caterings");
		
		return null;

	}

}
