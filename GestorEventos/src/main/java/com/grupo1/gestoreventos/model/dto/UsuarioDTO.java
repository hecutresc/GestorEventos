package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;

import com.grupo1.gestoreventos.repository.entity.Direccion;
import com.grupo1.gestoreventos.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private int tipo;
	private String nombre;
	private String apellidos;
	private String nif;
	private String telefono;
	private String email;
	private String nombreUsuario;
	private String contrasena;
	
	@ToString.Exclude
	private Direccion direccion;
	
	public static UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setTipo(usuario.getTipo());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setApellidos(usuario.getApellidos());
		usuarioDTO.setNif(usuario.getNif());
		usuarioDTO.setTelefono(usuario.getTelefono());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setContrasena(usuario.getContrasena());
		usuarioDTO.setDireccion(usuario.getDireccion());
	
		return usuarioDTO;
	}
	
	public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setTipo(usuarioDTO.getTipo());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setNif(usuarioDTO.getNif());
		usuario.setTelefono(usuarioDTO.getTelefono());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setContrasena(usuarioDTO.getContrasena());
		usuario.setDireccion(usuarioDTO.getDireccion());
		
		return usuario;
	}


	
}