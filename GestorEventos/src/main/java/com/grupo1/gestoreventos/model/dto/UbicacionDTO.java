package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Catering;
import com.grupo1.gestoreventos.repository.entity.Evento;
import com.grupo1.gestoreventos.repository.entity.Ubicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data

public class UbicacionDTO {

	private Long id;

	@NotEmpty(message = "¡Pon el nombre de la Ubicación!")
	private String nombre;

	@NotEmpty(message = "¡Pon el aforo de la Ubicación!")
	@Pattern(regexp = "\\d+", message = "¡El aforo debe contener solo números enteros!")
	private String aforo;
	
	private String foto;
	
	@NotNull(message = "¡Pon el precio por hora!")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio por hora debe ser mayor que 0€")
    @DecimalMax(value = "100000.0", inclusive = true, message = "El precio por hora no puede ser mayor a 100.000€")
	private Float precio_hora;

	@ToString.Exclude
	@Valid
	private DireccionDTO direccionDTO;
	
	@ToString.Exclude
	private List<EventoDTO> listaEventosDTO;

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
		ubicacionDTO.setPrecio_hora(ubicacion.getPrecio_hora());
		ubicacionDTO.setFoto(ubicacion.getFoto());
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
		ubicacion.setPrecio_hora(ubicacionDTO.getPrecio_hora());
		ubicacion.setFoto(ubicacionDTO.getFoto());
		ubicacion.setDireccion(DireccionDTO.convertToEntity(ubicacionDTO.getDireccionDTO()));
		return ubicacion;
	}
	
	
	//Constructor
	public UbicacionDTO() {
		this.direccionDTO = new DireccionDTO();
		this.listaEventosDTO = new ArrayList<EventoDTO>();
		this.listaCateringUbicacionEventosDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}
	

}
