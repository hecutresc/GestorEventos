package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.InvitadoDTO;
import com.grupo1.gestoreventos.repository.dao.InvitadoRepository;
import com.grupo1.gestoreventos.repository.entity.Invitado;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvitadoServiceImpl implements InvitadoService {

	@Autowired
	private InvitadoRepository invitadoRepository;

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
		
		

		
	}

}
