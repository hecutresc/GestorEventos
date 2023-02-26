package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.CateringUbicacionEventoDTO;
import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.EventoRepository;
import com.grupo1.gestoreventos.repository.entity.CateringUbicacionEvento;
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

		//Transformamos la lista de datos a dtos
		List<CateringUbicacionEventoDTO> lista = new ArrayList<>();
		for (CateringUbicacionEvento c : evento.get().getListaCateringUbicacionEvento()) {
			CateringUbicacionEventoDTO ca = CateringUbicacionEventoDTO.convertToDTO(c);
			lista.add(ca);
		}
		//transformamos la ubicacion a ubicacionDTO
		UbicacionDTO ubicacionDTO = UbicacionDTO.convertToDTO(evento.get().getUbicacion());
		//Anyadimos la lista y la ubicacionDTO
		eventoDTO = EventoDTO.convertToDTO(evento.get());
		eventoDTO.setUbicacionDTO(ubicacionDTO);
		eventoDTO.setListaCateringubicacioneventoDTO(lista);

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
		
		List<Evento> eventos = eventoRepository.findAllByUser(usuarioDTO.getId());

		List<EventoDTO> eventosDTO = eventos.stream().map(EventoDTO::convertToDTO).collect(Collectors.toList());
		

		return eventosDTO;
	
	}

}
