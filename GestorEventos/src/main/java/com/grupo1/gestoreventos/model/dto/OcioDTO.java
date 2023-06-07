package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Ocio;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
public class OcioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//Atributos
	
	private Long id;
	
	@NotBlank(message = "¡El campo Nombre es obligatorio!")
	private String nombre;
	
	@NotBlank(message = "¡El campo Descripción es obligatorio!")
	private String descripcion;
	
	private String foto;
	
	@NotNull(message = "¡El campo Precio por hora es obligatorio!")
	@DecimalMin(value = "0.0", inclusive = true, message = "¡El precio por hora debe ser mayor o igual a 0.0!")
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
		ocioDTO.setEmpresaDTO(EmpresaDTO.convertToDTO(ocio.getEmpresa()));
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
		this.foto = " ";
		this.empresaDTO = new EmpresaDTO();
		this.listasCateringUbicacionEventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}
	

	
	
}
