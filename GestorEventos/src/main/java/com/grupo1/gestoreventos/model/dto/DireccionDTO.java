package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.grupo1.gestoreventos.repository.entity.Direccion;

import lombok.Data;
import lombok.ToString;

@Data
public class DireccionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String calle;
	private String numero;
	private String ciudad;
	private String cp;
	private String provincia;

	@ToString.Exclude
	private List<UsuarioDTO> listaUsuariosDTO;

	@ToString.Exclude
	private List<EmpresaDTO> listaEmpresasDTO;

	@ToString.Exclude
	private List<UbicacionDTO> listaUbicacionesDTO;

	public static DireccionDTO convertToDTO(Direccion direccion) {
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setId(direccion.getId());
		direccionDTO.setCalle(direccion.getCalle());
		direccionDTO.setNumero(direccion.getNumero());
		direccionDTO.setCiudad(direccion.getCiudad());
		direccionDTO.setCp(direccion.getCp());
		direccionDTO.setProvincia(direccion.getProvincia());
		return direccionDTO;
	}

	public static Direccion convertToEntity(DireccionDTO direccionDTO) {
		Direccion direccion = new Direccion();
		direccion.setId(direccionDTO.getId());
		direccion.setCalle(direccionDTO.getCalle());
		direccion.setNumero(direccionDTO.getNumero());
		direccion.setCiudad(direccionDTO.getCiudad());
		direccion.setCp(direccionDTO.getCp());
		direccion.setProvincia(direccionDTO.getProvincia());
		return direccion;
	}

	public DireccionDTO() {
		super();
		this.listaUsuariosDTO = new ArrayList<>();
		this.listaEmpresasDTO = new ArrayList<>();
		this.listaUbicacionesDTO = new ArrayList<>();
	}

}
