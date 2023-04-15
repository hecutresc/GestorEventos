package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Ocio;

import lombok.Data;
import lombok.ToString;

@Data
public class OcioDTO {
	
	//Atributos
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String foto;
	
	private Float precio_hora;
	
	@ToString.Exclude
	private EmpresaDTO empresaDTO;
	@ToString.Exclude
	private List<CateringUbicacionEventoDTO> listasCateringUbicacionEventoDTO;
	
	//Métodos conversores
	
	//ConvertToDTO
	
	public static OcioDTO convertToDTO(Ocio ocio) {
		//Creando los objeto necesarios
		OcioDTO ocioDTO = new OcioDTO();
		
		//Set de los atributos
		ocioDTO.setId(ocio.getId());
		ocioDTO.setNombre(ocio.getNombre());
		ocioDTO.setDescripcion(ocio.getDescripcion());
		ocioDTO.setFoto(ocio.getFoto());
		ocioDTO.setPrecio_hora(ocio.getPrecio_hora());
		return ocioDTO;
	}
	
	
	//ConvertToEntity
	public static Ocio convertToEntity(OcioDTO ocioDTO) {
		//Crear objetos auxiliares
		Ocio ocio = new Ocio();
		
		// Set  de los atributos
		
		ocio.setId(ocioDTO.getId());
		ocio.setNombre(ocioDTO.getNombre());
		ocio.setDescripcion(ocioDTO.getDescripcion());
		ocio.setFoto(ocioDTO.getFoto());
		ocio.setPrecio_hora(ocioDTO.getPrecio_hora());
		return ocio;
		
	}
	
	//Constructor
	public OcioDTO() {
		this.empresaDTO = new EmpresaDTO();
		this.listasCateringUbicacionEventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}
	

	
	
}
