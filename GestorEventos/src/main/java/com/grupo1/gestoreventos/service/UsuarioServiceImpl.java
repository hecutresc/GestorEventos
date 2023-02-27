package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.DireccionRepository;
import com.grupo1.gestoreventos.repository.dao.UsuarioRepository;
import com.grupo1.gestoreventos.repository.entity.Direccion;
import com.grupo1.gestoreventos.repository.entity.Rol;
import com.grupo1.gestoreventos.repository.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DireccionRepository direccionRepository;

	@Override
	public List<UsuarioDTO> findAll() {
		log.info("UsuarioServiceImpl - findAll: Lista de todos los Usuario");

		List<UsuarioDTO> listaUsuariosDTO = usuarioRepository.findAll().stream().map(p -> UsuarioDTO.convertToDTO(p))
				.collect(Collectors.toList());

		return listaUsuariosDTO;
	}

	@Override
	public UsuarioDTO findById(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - findById: Buscar Usuario por id: " + usuarioDTO.getId());

		Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getId());
		if (usuario.isPresent()) {
			usuarioDTO = UsuarioDTO.convertToDTO(usuario.get());
			return usuarioDTO;
		} else {
			return null;
		}
	}

	@Override
	public void save(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - save: Salvamos el Usuario: " + usuarioDTO.toString());

		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);

		if (usuarioDTO.getId() == null) {
			Direccion direccion = direccionRepository.save(usuario.getDireccion());
			usuario.setDireccion(direccion);
			usuarioRepository.save(usuario);
		} else {
			usuarioRepository.save(usuario);
		}

	}

	@Override
	public void delete(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - delete: Borramos el Usuario: " + usuarioDTO.getId());

		usuarioRepository.deleteById(usuarioDTO.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("UsuarioServiceImpl - loadUserByUsername: " + username);
		Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
		if(usuario != null) {
			List<GrantedAuthority> listaPermisos = new ArrayList<GrantedAuthority>();
			List<Rol> listaRoles = new ArrayList<Rol>(usuario.get().getListaRoles());
			for (Rol rol : listaRoles) {
				listaPermisos.add(new SimpleGrantedAuthority(rol.getNombre()));
			}
			return new User(usuario.get().getNombreUsuario(), usuario.get().getClaveAcceso(), listaPermisos);
		}else {
			throw new UsernameNotFoundException(username);
		}
		
	}

	@Override
	public UsuarioDTO findByUsernameAndPassword(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);
		log.info("Contraseña: "+usuario.getClaveAcceso());
		Optional<Usuario> usr = usuarioRepository.findByUsername(usuario.getNombreUsuario());
		if(usr.isPresent()) {
			Boolean a = new BCryptPasswordEncoder().matches(usuarioDTO.getClaveAcceso(), usr.get().getClaveAcceso());
			if(a) {
				usuarioDTO = UsuarioDTO.convertToDTO(usr.get());
				return usuarioDTO;
			}else {
				return null;
			}
			
		}else {
			return null;
		}
		
	}
}
