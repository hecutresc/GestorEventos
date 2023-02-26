package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;

public interface EventoService {

	public List<EventoDTO> findAllByUser(UsuarioDTO usuarioDTO);

}
