package com.grupo1.gestoreventos.web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.OcioDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.EmpresaService;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.OcioService;

import jakarta.validation.Valid;


@Controller
public class OcioController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(OcioController.class);

	// Objetos Autowired
	@Autowired
	private OcioService ocioService;

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private EmpresaService empresaService;

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
	public ModelAndView save(@PathVariable("idEmpresa") Long idEmpresa, @Valid @ModelAttribute("ocioDTO") OcioDTO ocioDTO, BindingResult bindingResult, @RequestParam("archivo") MultipartFile foto) {

		log.info("OcioController - save: Salvamos los datos del ocio:" + ocioDTO.toString());

		// Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		ocioDTO.setEmpresaDTO(empresaDTO);
		// Comprobamos si hay errores
		if(bindingResult.hasErrors() == true) {
			if(ocioDTO.getId() == null) {
				ModelAndView mav = new ModelAndView("app/ocioform");
				mav.addObject("empresaDTO", empresaDTO);
				mav.addObject("ocioDTO", ocioDTO);
				mav.addObject("add", true);

				return mav;
			}else {
				ModelAndView mav = new ModelAndView("app/ocioform");
				mav.addObject("empresaDTO", empresaDTO);
				mav.addObject("ocioDTO", ocioDTO);
				mav.addObject("add", false);

				return mav;
			}
		}
		
		if(ocioDTO.getId() == null) {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/images"));
				
				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/images/" + foto.getOriginalFilename());
				
				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ocioDTO.setFoto("/images/"+foto.getOriginalFilename());
		} else if(ocioDTO.getId() != null && foto.getOriginalFilename() != "") {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/images"));
				
				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/images/" + foto.getOriginalFilename());
				
				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ocioDTO.setFoto("/images/"+foto.getOriginalFilename());
		}
		
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

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/ocio")
	public ModelAndView findByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("OcioController - findByEvento: Muestra el Ocio del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		OcioDTO ocioDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ocioshow");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("ocioDTO", ocioDTO);

		return mv;

	}

	@GetMapping("/admin/empresas/{idEmpresa}/ocios/{idOcio}")
	public ModelAndView findByEvento2(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idOcio") Long idOcio) {
		log.info("OcioController - findByEvento2: Muestra el Ocio: " + idOcio);

		// Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		empresaDTO = empresaService.findById(empresaDTO);
		
		OcioDTO ocioDTO = new OcioDTO();
		ocioDTO.setId(idOcio);
		ocioDTO = ocioService.findById(ocioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ocioshow2");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("ocioDTO", ocioDTO);

		return mv;

	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/ocio")
	public ModelAndView findByEventoUser(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("OcioController - findByEvento: Muestra el Ocio del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		OcioDTO ocioDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getOcioDTO();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ocioshowuser");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("ocioDTO", ocioDTO);

		return mv;

	}

}
