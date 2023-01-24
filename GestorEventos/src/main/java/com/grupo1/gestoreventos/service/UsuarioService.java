package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;

public interface UsuarioService {

	List<UsuarioDTO> findAll();
	UsuarioDTO findById(UsuarioDTO UsuarioDTO);
	void save(UsuarioDTO UsuarioDTO);
	void delete(UsuarioDTO UsuarioDTO);

}
