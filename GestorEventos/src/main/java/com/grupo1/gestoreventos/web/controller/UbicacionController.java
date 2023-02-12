package com.grupo1.gestoreventos.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.service.UbicacionService;



@Controller
public class UbicacionController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(UbicacionController.class);
	
	//Objetos Autowired
	@Autowired
	private UbicacionService ubicacionService;
	
	//Métodos
	@GetMapping("/admin/ubicaciones")
	public ModelAndView findAll() {
		//Mostramos todas las Ubicaciones
		log.info("UbicacionController - findAll: Recoge todas las ubicaciones");
		List<UbicacionDTO> listaUbicacionesDTO = ubicacionService.findAll();
		
		//Mostramos los valores
		for (UbicacionDTO u : listaUbicacionesDTO) {
			System.out.println(u.toString());
		}
		
		// Mostramos la lista
		ModelAndView mav = new ModelAndView();
		mav.addObject("listaUbicacionesDTO", listaUbicacionesDTO);
		return null;
		
	}
	
	@GetMapping("/admin/ubicaciones/delete/{idUbicacion}")
	public ModelAndView delete(@PathVariable Long idUbicacion) {
		//Eliminamos la ubicación
		log.info("UbicacionController - delete: Elimina la ubicación "+idUbicacion);
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);
		
		//Llamamos al service
		ubicacionService.delete(ubicacionDTO);
		
		//Volvemos a la lista
		ModelAndView mav = new ModelAndView("redirect:/admin/ubicaciones");
		
		return null;
		
	}
}
