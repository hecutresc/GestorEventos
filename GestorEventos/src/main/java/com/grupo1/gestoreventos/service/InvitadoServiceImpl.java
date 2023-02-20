package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.InvitadoDTO;
import com.grupo1.gestoreventos.repository.dao.EventoRepository;
import com.grupo1.gestoreventos.repository.dao.InvitadoRepository;
import com.grupo1.gestoreventos.repository.entity.Evento;
import com.grupo1.gestoreventos.repository.entity.Invitado;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvitadoServiceImpl implements InvitadoService {

	@Autowired
	private InvitadoRepository invitadoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public List<InvitadoDTO> findAllByEvento(EventoDTO eventoDTO) {
		log.info("InvitadoServiceImpl - findAllByEvento: Muestra la lista de invitados del Evento: "
				+ eventoDTO.getId());

		List<Invitado> listaInvitados = invitadoRepository.findAllByEvento(eventoDTO.getId());

		List<InvitadoDTO> listaInvitadosDTO = listaInvitados.stream().map(InvitadoDTO::convertToDTO)
				.collect(Collectors.toList());

		return listaInvitadosDTO;
	}

	@Override
	public void save(InvitadoDTO invitadoDTO) {
		log.info("InvitadoServiceImpl - save: Guarda un invitado en el evento: " + invitadoDTO.getEventoDTO().getId());

		Optional<Evento> evento = eventoRepository.findById(invitadoDTO.getEventoDTO().getId());

		Invitado invitado = InvitadoDTO.convertToEntity(invitadoDTO);
		invitado.setEvento(evento.get());

		invitadoRepository.save(invitado);

	}

	@Override
	public InvitadoDTO findById(InvitadoDTO invitadoDTO) {
		log.info("InvitadoServiceImpl - findById: Busca el invitado con Id: " + invitadoDTO.getId());

		Optional<Invitado> invitado = invitadoRepository.findById(invitadoDTO.getId());

		invitadoDTO = InvitadoDTO.convertToDTO(invitado.get());

		return invitadoDTO;
	}

	@Override
	public void deleteById(InvitadoDTO invitadoDTO) {
		log.info("InvitadoServiceImpl - deleteById: Elimina el invitado con Id: " + invitadoDTO.getId());
		
		invitadoRepository.deleteById(invitadoDTO.getId());
	}

}
