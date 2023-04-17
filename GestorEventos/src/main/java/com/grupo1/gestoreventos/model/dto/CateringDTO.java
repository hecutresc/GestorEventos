package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Catering;
import lombok.Data;
import lombok.ToString;

@Data

public class CateringDTO {

	// Atributos
	private Long id;

	private String menu;

	private Float precio;

	@ToString.Exclude
	private EmpresaDTO empresaDTO;
	
	@ToString.Exclude
	private UbicacionDTO ubicacionDTO;
	
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
		return catering;
	}
	
	// Constructor
	public CateringDTO() {
		this.ubicacionDTO = new UbicacionDTO();
		this.empresaDTO = new EmpresaDTO();
		this.listasCateringUbicacionEventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}

}