package com.grupo1.gestoreventos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.DireccionDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.DireccionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DireccionController {

	@Autowired
	public DireccionService direccionService;

	@GetMapping("/admin/usuarios/{idUsuario}/direccion")
	public ModelAndView findByUsuario(@PathVariable("idUsuario") Long idUsuario) {
		log.info("DireccionController - findByUsuario: Muestra la direccion de un Usuario");

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		DireccionDTO direccionDTO = direccionService.findByUsuario(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direcciones");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("tipo", "usuario");

		return mv;
	}

	@GetMapping("/admin/empresas/{idEmpresa}/direccion")
	public ModelAndView findByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
		log.info("DireccionController - findByEmpresa: Muestra la direccion de una Empresa");

		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		DireccionDTO direccionDTO = direccionService.findByEmpresa(empresaDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direcciones");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("tipo", "empresa");

		return mv;
	}

	@GetMapping("/admin/ubicaciones/{idUbicacion}/direccion")
	public ModelAndView findByUbicacion(@PathVariable("idUbicacion") Long idUbicacion) {
		log.info("DireccionController - findByUbicacion: Muestra la direccion de una Ubicacion");

		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		DireccionDTO direccionDTO = direccionService.findByUbicacion(ubicacionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direcciones");
		mv.addObject("ubicacionDTO", ubicacionDTO);
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("tipo", "ubicacion");

		return mv;
	}

	@GetMapping("/admin/usuarios/{idUsuario}/direccion/{idDireccion}/update")
	public ModelAndView updateByUsuario(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idDireccion") Long idDireccion) {
		log.info("DireccionController - updateByUsuario: Edita la direccion: " + idDireccion + " del Usuario: "
				+ idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		DireccionDTO direccionDTO = direccionService.findByUsuario(usuarioDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direccionform");
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("tipo", "usuario");

		return mv;
	}

	@GetMapping("/admin/empresas/{idEmpresa}/direccion/{idDireccion}/update")
	public ModelAndView updateByEmpresa(@PathVariable("idEmpresa") Long idEmpresa,
			@PathVariable("idDireccion") Long idDireccion) {
		log.info("DireccionController - deleteByEmpresa: Borra la direccion: " + idDireccion + " de la Empresa: "
				+ idEmpresa);

		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		DireccionDTO direccionDTO = direccionService.findByEmpresa(empresaDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direccionform");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("tipo", "empresa");

		return mv;
	}

	@GetMapping("/admin/ubicaciones/{idUbicacion}/direccion/{idDireccion}/update")
	public ModelAndView updateByUbicacion(@PathVariable("idUbicacion") Long idUbicacion,
			@PathVariable("idDireccion") Long idDireccion) {
		log.info("DireccionController - deleteByUsuario: Borra la direccion: " + idDireccion + " de la Ubicacion: "
				+ idUbicacion);

		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		DireccionDTO direccionDTO = direccionService.findByUbicacion(ubicacionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/direccionform");
		mv.addObject("ubicacionDTO", ubicacionDTO);
		mv.addObject("direccionDTO", direccionDTO);
		mv.addObject("tipo", "ubicacion");

		return mv;
	}

	@PostMapping("/admin/usuarios/{idUsuario}/direccion/save")
	public ModelAndView saveByUsuario(@PathVariable("idUsuario") Long idUsuario,
			@ModelAttribute("direccionDTO") DireccionDTO direccionDTO) {
		log.info("DireccionController - saveByUsuario: Actualiza la direccion: " + direccionDTO.getId()
				+ " del Usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);

		/**
		 * VALIDACION
		 */

		direccionService.save(direccionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/usuarios/{idUsuario}/direccion");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("tipo", "usuario");

		return mv;
	}

	@PostMapping("/admin/empresas/{idEmpresa}/direccion/save")
	public ModelAndView saveByEmpresa(@PathVariable("idEmpresa") Long idEmpresa,
			@ModelAttribute("direccionDTO") DireccionDTO direccionDTO) {
		log.info("DireccionController - saveByEmpresa: Actualiza la direccion: " + direccionDTO.getId()
				+ " de la Empresa: " + idEmpresa);

		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		/**
		 * VALIDACION
		 */

		direccionService.save(direccionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/empresas/{idEmpresa}/direccion");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("tipo", "empresa");

		return mv;
	}

	@PostMapping("/admin/ubicaciones/{idUbicacion}/direccion/save")
	public ModelAndView saveByUbicacion(@PathVariable("idUbicacion") Long idUbicacion,
			@ModelAttribute("direccionDTO") DireccionDTO direccionDTO) {
		log.info("DireccionController - saveByEmpresa: Actualiza la direccion: " + direccionDTO.getId()
				+ " de la Ubicacion: " + idUbicacion);

		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		/**
		 * VALIDACION
		 */

		direccionService.save(direccionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/ubicaciones/{idUbicacion}/direccion");
		mv.addObject("ubicacionDTO", ubicacionDTO);
		mv.addObject("tipo", "ubicacion");

		return mv;
	}

}
