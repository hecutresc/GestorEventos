package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;

public interface EventoService {
	
	public List<EventoDTO> findAll();

	public void save(EventoDTO eventoDTO);

	public EventoDTO findById(EventoDTO eventoDTO);

	public void deleteById(EventoDTO eventoDTO);

	public void delete(EventoDTO eventoDTO);

	public List<EventoDTO> findAllByUser(UsuarioDTO usuarioDTO);

}
