package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.grupo1.gestoreventos.repository.entity.Rol;
import com.grupo1.gestoreventos.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String apellidos;
	private String nif;
	private String telefono;
	private String email;
	private String nombreUsuario;
	private String claveAcceso;

	@ToString.Exclude
	private DireccionDTO direccionDTO;

	@ToString.Exclude
	private List<EventoDTO> listaEventosDTO;
	
	@ToString.Exclude
	private List<RolDTO> listaRolesDTO;

	public static UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setApellidos(usuario.getApellidos());
		usuarioDTO.setNif(usuario.getNif());
		usuarioDTO.setTelefono(usuario.getTelefono());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setClaveAcceso(usuario.getClaveAcceso());
		usuarioDTO.setDireccionDTO(DireccionDTO.convertToDTO(usuario.getDireccion()));
		/*
		 * List<EventoDTO> listaEventosDTO = usuario.getListaEventos().stream().map(p ->
		 * EventoDTO.convertToDTO(p)) .collect(Collectors.toList());
		 * 
		 * usuarioDTO.setListaEventosDTO(listaEventosDTO);
		 */
		for (int i = 0; i < usuario.getListaRoles().size(); i++) {
			RolDTO rolDTO = RolDTO.converToDTO(usuario.getListaRoles().get(i) , usuarioDTO);
			usuarioDTO.getListaRolesDTO().add(rolDTO);
			}
		return usuarioDTO;
	}

	public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setNif(usuarioDTO.getNif());
		usuario.setTelefono(usuarioDTO.getTelefono());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setClaveAcceso(new BCryptPasswordEncoder().encode(usuarioDTO.getClaveAcceso()));
		
		usuario.setDireccion(DireccionDTO.convertToEntity(usuarioDTO.getDireccionDTO()));

		// for (EventoDTO objeto : usuarioDTO.getListaEventosDTO()) {
		// usuario.getListaEventos().add(EventoDTO.convertToEntity(objeto));
		// }
		for (int i = 0; i < usuarioDTO.getListaRolesDTO().size(); i++) {
			Rol rol = RolDTO.converToEntity(usuarioDTO.getListaRolesDTO().get(i), usuario);
			usuario.getListaRoles().add(rol);
			}
		return usuario;
	}

	public UsuarioDTO() {
		super();
		this.direccionDTO = new DireccionDTO();
		this.listaEventosDTO = new ArrayList<EventoDTO>();
		this.listaRolesDTO = new ArrayList<RolDTO>();
	}

	public UsuarioDTO(Long idUsuarioDTO) {
		super();
		this.id = idUsuarioDTO;
		this.direccionDTO = new DireccionDTO();
		this.listaEventosDTO = new ArrayList<EventoDTO>();
		this.listaRolesDTO = new ArrayList<RolDTO>();
	}

}
