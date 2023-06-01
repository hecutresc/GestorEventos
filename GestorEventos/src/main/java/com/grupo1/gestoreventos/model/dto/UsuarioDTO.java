package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.grupo1.gestoreventos.repository.entity.Rol;
import com.grupo1.gestoreventos.repository.entity.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "¡El campo Nombre es obligatorio!")
	private String nombre;
	@NotBlank(message = "¡El campo Apellidos es obligatorio!")
	private String apellidos;
	@NotBlank(message = "¡El campo NIF es obligatorio!")
	@Pattern(regexp = "[XYZxyz]?[0-9]{7,8}[A-Za-z]", message = "¡El campo NIF no es válido!")
	private String nif;
	@NotBlank(message = "¡El campo Teléfono es obligatorio!")
	@Pattern(regexp = "\\d{9}", message = "El campo Teléfono no es válido")
	private String telefono;
	@NotBlank(message = "¡El campo Email es obligatorio!")
	@Email(message = "El campo Email no es válido")
	private String email;
	@NotBlank(message = "¡El campo Nombre de Usuario es obligatorio!")
	private String nombreUsuario;
	@NotBlank(message = "¡El campo Clave de Acceso es obligatorio!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]+$", message = "¡La contraseña tiene que tener al mínimo 8 carácteres, una mayúscula, una minúscula y un número!")
	private String claveAcceso;

	@ToString.Exclude
	@Valid
	private DireccionDTO direccionDTO;

	@ToString.Exclude
	private List<RolDTO> listaRolesDTO;
	
	@ToString.Exclude
	private List<EventoDTO> listaEventosDTO;
	

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
		
		for (int i = 0; i < usuario.getListaRoles().size(); i++) {
			RolDTO rolDTO = RolDTO.converToDTO(usuario.getListaRoles().get(i), usuarioDTO);
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
