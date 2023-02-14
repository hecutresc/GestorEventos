package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.InvitadoDTO;

public interface InvitadoService {

	public List<InvitadoDTO> findAllByEvento(EventoDTO eventoDTO);

}
