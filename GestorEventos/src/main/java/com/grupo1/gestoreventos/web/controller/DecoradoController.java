package com.grupo1.gestoreventos.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.DecoradoDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.DecoradoService;
import com.grupo1.gestoreventos.service.EmpresaService;
import com.grupo1.gestoreventos.service.EventoService;

@Controller
public class DecoradoController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(DecoradoController.class);

	// Objetos Autowired

	@Autowired
	private DecoradoService decoradoService;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private EmpresaService empresaService;
	// Métodos del Controlador

	// Listado de todos los decorados
	@GetMapping("/admin/empresas/{idEmpresa}/decorados")
	public ModelAndView findByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
		// Mostramos todos los caterings
		log.info("DecoradoController - findByEmpresa: Recoge todos los decorados disponibles de la empresa "
				+ idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		List<DecoradoDTO> listaDecoradoDTO = decoradoService.findAllByEmpresa(empresaDTO);

		// Mostramos la lista a la vista
		ModelAndView mav = new ModelAndView("/app/decorados");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("listaDecoradoDTO", listaDecoradoDTO);

		return mav;
	}

	// Añadir los decorados
	@GetMapping("/admin/empresas/{idEmpresa}/decorados/add")
	public ModelAndView add(@PathVariable("idEmpresa") Long idEmpresa) {

		log.info("DecoradoController - add: Anyadimos un nuevo decorado " + idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		ModelAndView mav = new ModelAndView("app/decoradoform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("decoradoDTO", new DecoradoDTO());
		mav.addObject("add", true);

		return mav;
	}

	// Editar los decorados
	@GetMapping("/admin/empresas/{idEmpresa}/decorados/update/{idDecorado}")
	public ModelAndView update(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idDecorado") Long idDecorado) {

		log.info("DecoradoController - update: Modificamos el decorado: " + idDecorado);

		// Seteamos la empresa
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);

		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		DecoradoDTO decoradoDTO = new DecoradoDTO();
		decoradoDTO.setId(idDecorado);
		decoradoDTO = decoradoService.findById(decoradoDTO);

		ModelAndView mav = new ModelAndView("app/decoradoform");
		mav.addObject("empresaDTO", empresaDTO);
		mav.addObject("decoradoDTO", decoradoDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Guardar el Decorado Controller
	@PostMapping("/admin/empresas/{idEmpresa}/decorados/save")
	public ModelAndView save(@PathVariable("idEmpresa") Long idEmpresa,
			@ModelAttribute("decoradoDTO") DecoradoDTO decoradoDTO, @RequestParam("archivo") MultipartFile foto) {

		log.info("DecoradoController - save: Salvamos los datos del decorado:" + decoradoDTO.toString());

		// Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		decoradoDTO.setEmpresaDTO(empresaDTO);
		if (decoradoDTO.getId() == null) {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/imagesDecorados"));

				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/imagesDecorados/" + foto.getOriginalFilename());

				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			decoradoDTO.setFoto("/images/" + foto.getOriginalFilename());
		} else if (decoradoDTO.getId() != null && foto.getOriginalFilename() != "") {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/imagesDecorados"));

				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/imagesDecorados/" + foto.getOriginalFilename());

				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			decoradoDTO.setFoto("/imagesDecorados/" + foto.getOriginalFilename());
		}

		// Invocamos a la capa de servicios para que almacene los datos del cliente
		decoradoService.save(decoradoDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/decorados");
		return mav;

	}

	@GetMapping("/admin/empresas/{idEmpresa}/decorados/delete/{idDecorado}")
	public ModelAndView delete(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable Long idDecorado) {
		// Eliminamos el catering
		log.info("DecoradoController - delete: Elimina el decorado " + idDecorado);
		DecoradoDTO decoradoDTO = new DecoradoDTO();
		decoradoDTO.setId(idDecorado);

		// Llamamos al service
		decoradoService.delete(decoradoDTO);

		// Volvemos a la vista principal
		ModelAndView mav = new ModelAndView("redirect:/admin/empresas/{idEmpresa}/decorados");

		return mav;

	}

	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/decorado")
	public ModelAndView findByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("DecoradoController - findByEvento: Muestra el Decorado del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		DecoradoDTO decoradoDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ocioshow");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("decoradoDTO", decoradoDTO);

		return mv;

	}

	@GetMapping("/admin/empresas/{idEmpresa}/decorados/{idDecorado}")
	public ModelAndView findByEvento2(@PathVariable("idEmpresa") Long idEmpresa, @PathVariable("idDecorado") Long idDecorado) {
		log.info("DecoradoController - findByEvento2: Muestra el Decorado: " + idDecorado);

		// Seteamos la empresa al nuevo Catering
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(idEmpresa);
		empresaDTO = empresaService.findById(empresaDTO);

		DecoradoDTO decoradoDTO = new DecoradoDTO();
		decoradoDTO.setId(idDecorado);
		decoradoDTO = decoradoService.findById(decoradoDTO);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/decoradoshow2");
		mv.addObject("empresaDTO", empresaDTO);
		mv.addObject("decoradoDTO", decoradoDTO);

		return mv;

	}
	
	
	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/decorado")
	public ModelAndView findByEventoUser(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("DecoradoController - findByEvento: Muestra el Decorado del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		DecoradoDTO decoradoDTO = eventoDTO.getListaCateringubicacioneventoDTO().get(0).getDecoradoDTO();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/decoradoshowuser");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("decoradoDTO", decoradoDTO);

		return mv;

	}

}
