package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;
import com.grupo1.gestoreventos.repository.entity.Ubicacion;

import lombok.Data;
import lombok.ToString;

@Data

public class UbicacionDTO {

	private Long id;

	private String nombre;

	private String aforo;

	@ToString.Exclude
	private DireccionDTO direccionDTO;

	@ToString.Exclude
	private List<CateringUbicacionEventoDTO> listaCateringUbicacionEventosDTO;
	
	
	//Métodos Conversores
	
	//ConvertToDTO
	public static UbicacionDTO convertToDTO(Ubicacion ubicacion) {
		//Creando los objetos y listas necesarios
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		
		//Set de los atributos
		ubicacionDTO.setId(ubicacion.getId());
		ubicacionDTO.setNombre(ubicacion.getNombre());
		ubicacionDTO.setAforo(ubicacion.getAforo());
		ubicacionDTO.setDireccionDTO(DireccionDTO.convertToDTO(ubicacion.getDireccion()));
		
		
		return ubicacionDTO;
	}
	
	//ConvertToEntity
	public static Ubicacion convertToEntity(UbicacionDTO ubicacionDTO) {
		//Creando los objetos y listas necesarios
		Ubicacion ubicacion = new Ubicacion();
		
		//Set de los atributos
		ubicacion.setId(ubicacionDTO.getId());
		ubicacion.setNombre(ubicacionDTO.getNombre());
		ubicacion.setAforo(ubicacionDTO.getAforo());
		ubicacion.setDireccion(DireccionDTO.convertToEntity(ubicacionDTO.getDireccionDTO()));
		
		return ubicacion;
	}
	
	
	//Constructor
	public UbicacionDTO() {
		this.direccionDTO = new DireccionDTO();
		this.listaCateringUbicacionEventosDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}
	

}
