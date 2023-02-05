package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.UsuarioRepository;
import com.grupo1.gestoreventos.repository.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDTO> findAll() {		
		log.info("UsuarioServiceImpl - findAll: Lista de todos los Usuario");

		List<UsuarioDTO> listaUsuariosDTO = usuarioRepository.findAll()
				.stream()
				.map(p->UsuarioDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		return listaUsuariosDTO;
	}

	@Override
	public UsuarioDTO findById(UsuarioDTO usuarioDTO) {		
		log.info("UsuarioServiceImpl - findById: Buscar Usuario por id: " + usuarioDTO.getId());
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getId());
		if(usuario.isPresent()) {
			usuarioDTO = UsuarioDTO.convertToDTO(usuario.get());
			return usuarioDTO;
		}else {
			return null;
		}
	}

	@Override
	public void save(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - save: Salvamos el Usuario: " + usuarioDTO.toString());
		
		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);
		usuarioRepository.save(usuario);
	}

	@Override
	public void delete(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - delete: Borramos el Usuario: " + usuarioDTO.getId());
		
		usuarioRepository.deleteById(usuarioDTO.getId());
	}
}
