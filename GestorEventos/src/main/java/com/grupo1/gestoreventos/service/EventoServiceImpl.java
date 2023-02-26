package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.EventoRepository;
import com.grupo1.gestoreventos.repository.entity.Evento;

@Service
public class EventoServiceImpl implements EventoService{

	private static final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Override
	public List<EventoDTO> findAll() {
		
		List<Evento> listaEventos = eventoRepository.findAll();
		
		List<EventoDTO> listaEventosDTO = new ArrayList<>();
		
		for (Evento evento : listaEventos) {
			listaEventosDTO.add(EventoDTO.convertToDTO(evento));
		}
		
		return listaEventosDTO;
	
	}

	@Override
	public List<EventoDTO> findAllByUsuario(UsuarioDTO usuarioDTO) {
		
		List<Evento> listaEventos = eventoRepository.findAllByUsuario(usuarioDTO.getId());
		
		List<EventoDTO> listaEventosDTO = new ArrayList<>();
		
		for (Evento evento : listaEventos) {
			listaEventosDTO.add(EventoDTO.convertToDTO(evento));
		}
		
		return listaEventosDTO;
	}
	
	@Override
	public void save(EventoDTO eventoDTO) {
		log.info("EventoServiceImpl - save: Guarda un evento: " + eventoDTO.getId());

		Optional<Evento> evento = eventoRepository.findById(eventoDTO.getId());

		if (evento.isPresent()) {
			eventoRepository.save(evento.get());
		}
		
	}

	@Override
	public EventoDTO findById(EventoDTO eventoDTO) {
		log.info("EventoServiceImpl - findById: Busca el evento con Id: " + eventoDTO.getId());

		Optional<Evento> evento = eventoRepository.findById(eventoDTO.getId());

		eventoDTO = EventoDTO.convertToDTO(evento.get());

		return eventoDTO;
	}

	@Override
	public void deleteById(EventoDTO eventoDTO) {
		log.info("EventoServiceImpl - deleteById: Elimina el evento con Id: " + eventoDTO.getId());
		
		eventoRepository.deleteById(eventoDTO.getId());
	}

	@Override
	public void delete(EventoDTO eventoDTO) {
		
		Optional<Evento> evento = eventoRepository.findById(eventoDTO.getId());

		if (evento.isPresent()) {
			eventoRepository.delete(evento.get());
		}
		
		
	}
	
	@Override
	public List<EventoDTO> findAllByUser(UsuarioDTO usuarioDTO) {
		log.info("EventoServiceImpl - findAllByUser: Busca la lista de Eventos del Usuario: " + usuarioDTO.getId());
		/*
		List<Evento> eventos = eventoRepository.findAllByUser(usuarioDTO.getId());

		for (Evento evento : eventos) {

			System.out.println("=====================================");
			System.out.println(evento.toString());
			System.out.println(evento.getUsuario().toString());
			System.out.println(evento.getUbicacion().toString());
			System.out.println("=====================================");
		}

		List<EventoDTO> eventosDTO = eventos.stream().map(EventoDTO::convertToDTO).collect(Collectors.toList());
		
		for (EventoDTO evento : eventosDTO) {

			System.out.println("=====================================");
			System.out.println(evento.toString());
			System.out.println(evento.getUsuarioDTO().toString());
		//	System.out.println(evento.getUbicacionDTO().toString());
			System.out.println("=====================================");
		}

		return eventosDTO;
		*/
		return null;
	}

}
