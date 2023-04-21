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

	// Añadir los ocios
	@GetMapping("/admin/empresas/{idEmpresa}/ocios/add")
	public ModelAndView add(@PathVariable("idEmpresa") Long idEmpresa) {

		log.info("OcioController - add: Anyadimos un nuevo ocio " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		ModelAndView mav = new ModelAndView("app/ocioform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("ocioDTO", new OcioDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Update
	@GetMapping("/admin/empresas/{idEmpresa}/ocios/update/{idOcio}")
	public ModelAndView update(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idOcio") Long idOcio) {

		log.info("OcioController - update: Modificamos el ocio: " + idOcio);

		// Seteamos la empresa
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		OcioDTO ocioDTO = new OcioDTO();
		ocioDTO.setId(idOcio);
		ocioDTO = ocioService.findById(ocioDTO);

		ModelAndView mav = new ModelAndView("app/ocioform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("ocioDTO", ocioDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Save
	@PostMapping("/admin/empresas/{idEmpresa}/ocios/save")
	public ModelAndView save(@PathVariable("idEmpresa") Long idEmpresa, @ModelAttribute("ocioDTO") OcioDTO ocioDTO) {

		log.info("OcioController - save: Salvamos los datos del ocio:" + ocioDTO.toString());

		// Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		ocioDTO.setEmpresaDTO(empresaDTO);

		// Invocamos a la capa de servicios para que almacene los datos del cliente
		ocioService.save(ocioDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/ocios");
		return mav;

	}

	@GetMapping("/admin/empresas/{idEmpresa}/ocios/delete/{idOcio}")
	public ModelAndView delete(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable Long idOcio) {
		// Eliminamos el catering
		log.info("CateringController - delete: Elimina el ocio " + idOcio);
		OcioDTO ocioDTO = new OcioDTO();
		ocioDTO.setId(idOcio);

		// Llamamos al service
		ocioService.delete(ocioDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/ocios");

		return mav;

	}

}
