package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;

import com.grupo1.gestoreventos.repository.entity.Rol;
import com.grupo1.gestoreventos.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class RolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos
	private Long id;
	private String nombre;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;

	// Metodos
	public static RolDTO converToDTO(Rol rol, UsuarioDTO usuarioDTO) {
		RolDTO rolDTO = new RolDTO();
		rolDTO.setId(rol.getId());
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setUsuarioDTO(usuarioDTO);
		return rolDTO;
	}

	public static Rol converToEntity(RolDTO rolDTO, Usuario usuario) {
		Rol rol = new Rol();
		rol.setId(rolDTO.getId());
		rol.setNombre(rolDTO.getNombre());
		rol.setUsuario(usuario);
		return rol;
	}

	// Constructor
	public RolDTO() {
		this.usuarioDTO = new UsuarioDTO();
	}

}
