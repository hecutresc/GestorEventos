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

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView("app/empresas");
		mav.addObject("listaEmpresasDTO", listaEmpresasDTO);
		return mav;

	}

	// Alta de clientes
	@GetMapping("/admin/empresas/add")
	public ModelAndView add() {
		log.info("EmpresaController - add: Anyadimos una nueva empresa");
		ModelAndView mav = new ModelAndView("app/empresaform");
		mav.addObject("empresaDTO", new EmpresaDTO());
		mav.addObject("add", true);
		return mav;
	}

	// Actualizar la informacion de un cliente
	@GetMapping("/admin/empresas/update/{idEmpresa}")
	public ModelAndView update(@PathVariable("idEmpresa") Long idEmpresa) {

		log.info("ClienteController - update: Modificamos la empresa: " + idEmpresa);

		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		empresaDTO = empresaService.findById(empresaDTO);
		ModelAndView mav = new ModelAndView("app/empresaform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("add", false);
		return mav;
	}

	// Salvar clientes
	@PostMapping("/admin/empresas/save")
	public ModelAndView save(@ModelAttribute("empresaDTO") EmpresaDTO empresaDTO) {

		log.info("EmpresasController - save: Salvamos los datos de la empresa:" + empresaDTO.toString());
		
		
		// Invocamos a la capa de servicios para que almacene los datos del cliente
		empresaService.save(empresaDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas");
		return mav;

	}

	@GetMapping("/admin/empresas/delete/{idEmpresa}")
	public ModelAndView delete(@PathVariable("idEmpresa") Long idEmpresa) {
		// Mostramos todos los caterings
		log.info("EmpresasController - delete: Elimina la empresa " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		// Llamamnos al service
		empresaService.delete(empresaDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas");
		return mav;

	}

}
