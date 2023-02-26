package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.EventoRepository;
import com.grupo1.gestoreventos.repository.entity.Evento;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public List<EventoDTO> findAllByUser(UsuarioDTO usuarioDTO) {
		log.info("EventoServiceImpl - findAllByUser: Busca la lista de Eventos del Usuario: " + usuarioDTO.getId());

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
	}

}
