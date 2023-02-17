package com.grupo1.gestoreventos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		mv.setViewName("");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("direccionDTO", direccionDTO);
		
		
		System.out.println(direccionDTO.toString());
		return null;
	}

	@GetMapping("/admin/empresas/{idEmpresa}/direccion")
	public ModelAndView findByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
		log.info("DireccionController - findByEmpresa: Muestra la direccion de una Empresa");

		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		DireccionDTO direccionDTO = direccionService.findByEmpresa(empresaDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("direccionDTO", direccionDTO);

		System.out.println(direccionDTO.toString());
		return null;
	}

	@GetMapping("/admin/ubicaciones/{idUbicacion}/direccion")
	public ModelAndView findByUbicacion(@PathVariable("idUbicacion") Long idUbicacion) {
		log.info("DireccionController - findByUbicacion: Muestra la direccion de una Ubicacion");

		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		DireccionDTO direccionDTO = direccionService.findByUbicacion(ubicacionDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("");
		mv.addObject("ubicacionDTO", ubicacionDTO);
		mv.addObject("direccionDTO", direccionDTO);

		System.out.println(direccionDTO.toString());
		return null;
	}
}
