package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo1.gestoreventos.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int tipo;
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

	public static UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setTipo(usuario.getTipo());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setApellidos(usuario.getApellidos());
		usuarioDTO.setNif(usuario.getNif());
		usuarioDTO.setTelefono(usuario.getTelefono());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setClaveAcceso(usuario.getClaveAcceso());
		usuarioDTO.setDireccionDTO(DireccionDTO.convertToDTO(usuario.getDireccion()));

		List<EventoDTO> listaEventosDTO = usuario.getListaEventos().stream().map(p -> EventoDTO.convertToDTO(p))
				.collect(Collectors.toList());

		usuarioDTO.setListaEventosDTO(listaEventosDTO);

		return usuarioDTO;
	}

	public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setTipo(usuarioDTO.getTipo());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setNif(usuarioDTO.getNif());
		usuario.setTelefono(usuarioDTO.getTelefono());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setClaveAcceso(usuarioDTO.getClaveAcceso());
		usuario.setDireccion(DireccionDTO.convertToEntity(usuarioDTO.getDireccionDTO()));

		for (EventoDTO objeto : usuarioDTO.getListaEventosDTO()) {
			usuario.getListaEventos().add(EventoDTO.convertToEntity(objeto));
		}

		return usuario;
	}

	public UsuarioDTO() {
		super();
		this.listaEventosDTO = new ArrayList<EventoDTO>();
	}

	public UsuarioDTO(Long idUsuarioDTO) {
		super();
		this.id = idUsuarioDTO;
		this.listaEventosDTO = new ArrayList<EventoDTO>();
	}

}
