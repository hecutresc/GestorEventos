package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;
import com.grupo1.gestoreventos.repository.entity.Empresa;
import lombok.Data;
import lombok.ToString;

@Data

public class EmpresaDTO {

	private Long id;
	private String cif;
	private String nombre;
	@ToString.Exclude
	private DireccionDTO direccionDTO;
	@ToString.Exclude
	private List<CateringDTO> listaCateringsDTO;
	
	//Metodos de Conversión
	
	//ConvertToDTO
	public static EmpresaDTO convertToDTO(Empresa empresa) {
		//Creando los objetos y listas necesarios
		EmpresaDTO empresaDTO = new EmpresaDTO();
		
		//Set de los atributos
		empresaDTO.setId(empresa.getId());
		empresaDTO.setCif(empresa.getCif());
		empresaDTO.setNombre(empresa.getNombre());
		empresaDTO.setDireccionDTO(DireccionDTO.convertToDTO(empresa.getDireccion()));
		return empresaDTO;
	}
	
	//ConvertToEntity
	public static Empresa convertToEntity(EmpresaDTO empresaDTO) {
		//Creando los objetos y listas necesarios
		Empresa empresa = new Empresa();
		//Set de los atributos
		empresa.setId(empresaDTO.getId());
		empresa.setCif(empresaDTO.getCif());
		empresa.setNombre(empresaDTO.getNombre());
		empresa.setDireccion(DireccionDTO.convertToEntity(empresaDTO.getDireccionDTO()));
		return empresa;
	}
	
	
	//Constructor
	public EmpresaDTO() {
		this.direccionDTO = new DireccionDTO();
		this.listaCateringsDTO = new ArrayList<CateringDTO>();
	}
}