package com.grupo1.gestoreventos.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.naming.Binding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.UbicacionService;
import com.grupo1.gestoreventos.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class UbicacionController {

	// Log
	private static final Logger log = LoggerFactory.getLogger(UbicacionController.class);

	// Objetos Autowired
	@Autowired
	private UbicacionService ubicacionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EventoService eventoService;

	// Métodos
	@GetMapping("/admin/ubicaciones")
	public ModelAndView findAll() {
		// Mostramos todas las Ubicaciones
		log.info("UbicacionController - findAll: Recoge todas las ubicaciones");
		List<UbicacionDTO> listaUbicacionesDTO = ubicacionService.findAll();

		// Mostramos la lista
		ModelAndView mav = new ModelAndView("app/ubicaciones");
		mav.addObject("listaUbicacionesDTO", listaUbicacionesDTO);
		return mav;

	}

	// Alta de clientes
	@GetMapping("/admin/ubicaciones/add")
	public ModelAndView add() {

		log.info("UbicacionesController - add: Anyadimos una nueva ubicacion ");

		ModelAndView mav = new ModelAndView("app/ubicacionform");
		mav.addObject("ubicacionDTO", new UbicacionDTO());
		mav.addObject("add", true);
		return mav;
	}

	// Actualizar la informacion de un cliente
	@GetMapping("/admin/ubicaciones/update/{idUbicacion}")
	public ModelAndView update(@PathVariable("idUbicacion") Long idUbicacion) {

		log.info("UbicacionController - update: Modificamos la ubicación: " + idUbicacion);
		// Obtenemos el cliente y lo pasamos al modelo para ser actualizado
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);
		ubicacionDTO = ubicacionService.findById(ubicacionDTO);

		ModelAndView mav = new ModelAndView("app/ubicacionform");
		mav.addObject("ubicacionDTO", ubicacionDTO);
		mav.addObject("add", false);

		return mav;
	}

	// Salvar clientes
	@PostMapping("/admin/ubicaciones/save")
	public ModelAndView save(@Valid @ModelAttribute("ubicacionDTO") UbicacionDTO ubicacionDTO, BindingResult bindingResult, @RequestParam("archivo") MultipartFile foto) {

		log.info("UbicacionController - save: Salvamos los datos de la ubicaion:" + ubicacionDTO.toString());

		//Comprobamos que no tiene fallos
		if(bindingResult.hasErrors() == true) {
			if(ubicacionDTO.getId() == null) {
				ModelAndView mav = new ModelAndView("app/ubicacionform");
				mav.addObject("ubicacionDTO", ubicacionDTO);
				mav.addObject("add", true);
				return mav;
			}else {
				ModelAndView mav = new ModelAndView("app/ubicacionform");
				mav.addObject("ubicacionDTO", ubicacionDTO);
				mav.addObject("add", false);

				return mav;
			}
		}
		
		//Guardamos la foto
		if (ubicacionDTO.getId() == null) {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/imagesUbicaciones"));

				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/imagesUbicaciones/" + foto.getOriginalFilename());

				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ubicacionDTO.setFoto("/imagesUbicaciones/" + foto.getOriginalFilename());
		} else if (ubicacionDTO.getId() != null && foto.getOriginalFilename() != "") {
			try {
				Files.createDirectories(Paths.get("src/main/resources/static/imagesUbicaciones"));

				byte[] bytes = foto.getBytes();
				Path ruta = Paths.get("src/main/resources/static/imagesUbicaciones/" + foto.getOriginalFilename());

				Files.write(ruta, bytes);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ubicacionDTO.setFoto("/imagesUbicaciones/" + foto.getOriginalFilename());
		}
		
		// Invocamos a la capa de servicios para que almacene los datos del cliente
		ubicacionService.save(ubicacionDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /clientes
		ModelAndView mav = new ModelAndView("redirect:/admin/ubicaciones");
		return mav;

	}

	@GetMapping("/admin/ubicaciones/delete/{idUbicacion}")
	public ModelAndView delete(@PathVariable Long idUbicacion) {
		// Eliminamos la ubicación
		log.info("UbicacionController - delete: Elimina la ubicación " + idUbicacion);
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);

		// Llamamos al service
		ubicacionService.delete(ubicacionDTO);

		// Volvemos a la lista
		ModelAndView mav = new ModelAndView("redirect:/admin/ubicaciones");

		return mav;

	}

	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/ubicacion")
	public ModelAndView findByEventoUser(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId) {
		log.info("UbicacionController - findByEvento: Muestra la Ubicacion del Evento: " + idEvento);
			UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
			usuarioDTO = usuarioService.findById(usuarioDTO);
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			EventoDTO eventoDTO = new EventoDTO(idEvento);
			eventoDTO = eventoService.findById(eventoDTO);

			UbicacionDTO ubicacionDTO = ubicacionService.findById(eventoDTO.getUbicacionDTO());
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("app/ubicacionshowuser");
			mv.addObject("usuarioDTO", usuarioDTO);
			mv.addObject("eventoDTO", eventoDTO);
			mv.addObject("ubicacionDTO", ubicacionDTO);

			return mv;
		}else {
			ModelAndView mav = new ModelAndView("errors/403");
			return mav;
		}
		

	}
	
	@GetMapping("/admin/usuarios/{idUsuario}/eventos/{idEvento}/ubicacion")
	public ModelAndView findByEvento(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("idEvento") Long idEvento) {
		log.info("UbicacionController - findByEvento: Muestra la Ubicacion del Evento: " + idEvento);

		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		EventoDTO eventoDTO = new EventoDTO(idEvento);
		eventoDTO = eventoService.findById(eventoDTO);

		UbicacionDTO ubicacionDTO = ubicacionService.findById(eventoDTO.getUbicacionDTO());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("app/ubicacionshow");
		mv.addObject("usuarioDTO", usuarioDTO);
		mv.addObject("eventoDTO", eventoDTO);
		mv.addObject("ubicacionDTO", ubicacionDTO);

		return mv;

	}
	
	@GetMapping("/admin/ubicaciones/{idUbicacion}")
	public ModelAndView showUbicacion(@PathVariable("idUbicacion") Long idUbicacion) {
		
		//Buscamos la ubicacion
		UbicacionDTO ubicacionDTO =  new UbicacionDTO();
		ubicacionDTO.setId(idUbicacion);
		ubicacionDTO = ubicacionService.findById(ubicacionDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("ubicacionDTO", ubicacionDTO);
		mv.setViewName("app/ubicacion_info");
		return mv;
	}
}
