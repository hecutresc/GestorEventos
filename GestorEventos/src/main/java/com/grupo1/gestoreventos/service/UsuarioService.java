package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;

public interface UsuarioService {

	public List<UsuarioDTO> findAll();
	public UsuarioDTO findByUsername(UsuarioDTO UsuarioDTO);
	public UsuarioDTO findById(UsuarioDTO UsuarioDTO);
	public void save(UsuarioDTO UsuarioDTO);
	public void save2(UsuarioDTO UsuarioDTO);
	public void saveCookie(UsuarioDTO usuarioDTO);
	public void delete(UsuarioDTO UsuarioDTO);

}
