package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Catering;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data

public class CateringDTO {

	// Atributos
	private Long id;

	@NotBlank(message = "¡El campo Menú es obligatorio!")
	private String menu;

	@NotNull(message = "¡El campo Precio es obligatorio!")
	@DecimalMin(value = "0.0", inclusive = true, message = "¡El precio debe ser mayor o igual a 0.0!")
	private Float precio;
	
	private String foto;

	@ToString.Exclude
	private EmpresaDTO empresaDTO;
	
	@ToString.Exclude
	private List<CateringUbicacionEventoDTO> listasCateringUbicacionEventoDTO;

	// Métodos convertores

	// ConvertToDTO
	public static CateringDTO convertToDTO(Catering catering) {
		// Creando los objetos y listas necesarios
		CateringDTO cateringDTO = new CateringDTO();
		
		// Set de los atributos
		cateringDTO.setId(catering.getId());
		cateringDTO.setMenu(catering.getMenu());
		cateringDTO.setPrecio(catering.getPrecio());
		cateringDTO.setFoto(catering.getFoto());
		cateringDTO.setEmpresaDTO(EmpresaDTO.convertToDTO(catering.getEmpresa()));
		return cateringDTO;
	}

	// ConvertToEntity
	public static Catering convertToEntity(CateringDTO cateringDTO) {
		// Creando los objetos y listas necesarios
		Catering catering = new Catering();
		
		// Set de los atributos
		catering.setId(cateringDTO.getId());
		catering.setMenu(cateringDTO.getMenu());
		catering.setPrecio(cateringDTO.getPrecio());
		catering.setFoto(cateringDTO.getFoto());
		return catering;
	}
	
	// Constructor
	public CateringDTO() {
		this.empresaDTO = new EmpresaDTO();
		this.listasCateringUbicacionEventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}

}