package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo1.gestoreventos.repository.entity.Direccion;

import lombok.Data;
import lombok.ToString;

@Data
public class DireccionDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String calle;
	private String numero;
	private String ciudad;
	private String cp;
	
	@ToString.Exclude
	private List<UsuarioDTO> listaUsuariosDTO;
	
	@ToString.Exclude
	private List<EmpresaDTO> listaEmpresasDTO;
	
	@ToString.Exclude
	private List<UbicacionDTO> listaUbicacionesDTO;
	
	
	public static DireccionDTO convertToDTO(Direccion direccion) {
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setCalle(direccion.getCalle());
		direccionDTO.setNumero(direccion.getNumero());
		direccionDTO.setCiudad(direccion.getCiudad());
		direccionDTO.setCp(direccion.getCp());
		
		List<UsuarioDTO> listaUsuariosDTO = direccion.getListaUsuarios()
				.stream().map(p->UsuarioDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		List<EmpresaDTO> listaEmpresasDTO = direccion.getListaEmpresas()
				.stream().map(p->EmpresaDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		List<UbicacionDTO> listaUbicacionesDTO = direccion.getListaUbicaciones()
				.stream().map(p->UbicacionDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		direccionDTO.setListaUsuariosDTO(listaUsuariosDTO);
		direccionDTO.setListaEmpresasDTO(listaEmpresasDTO);
		direccionDTO.setListaUbicacionesDTO(listaUbicacionesDTO);
		
		return direccionDTO;
	}
	
	public static Direccion convertToEntity(DireccionDTO direccionDTO) {
		Direccion direccion = new Direccion();
		direccion.setCalle(direccionDTO.getCalle());
		direccion.setNumero(direccionDTO.getNumero());
		direccion.setCiudad(direccionDTO.getCiudad());
		direccion.setCp(direccionDTO.getCp());
		
		for (UsuarioDTO objeto : direccionDTO.getListaUsuariosDTO()) {
			direccion.getListaUsuarios().add(UsuarioDTO.convertToEntity(objeto));
		}
		
		for (EmpresaDTO objeto : direccionDTO.getListaEmpresasDTO()) {
			direccion.getListaEmpresas().add(EmpresaDTO.convertToEntity(objeto));
		}
		
		for (UbicacionDTO objeto : direccionDTO.getListaUbicacionesDTO()) {
			direccion.getListaUbicaciones().add(UbicacionDTO.convertToEntity(objeto));
		}
		
		return direccion;
	}

	public DireccionDTO() {
		super();
		this.listaUsuariosDTO = new ArrayList<>();
		this.listaEmpresasDTO = new ArrayList<>();
		this.listaUbicacionesDTO = new ArrayList<>();
	}

}
