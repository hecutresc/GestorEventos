package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grupo1.gestoreventos.repository.entity.Decorado;

import lombok.Data;
import lombok.ToString;

@Data
public class DecoradoDTO implements Serializable{


	private static final long serialVersionUID = 1L;

	//Atributos
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String foto;
	
	private Float precio;
	
	@ToString.Exclude
	private EmpresaDTO empresaDTO;
	@ToString.Exclude
	private List<CateringUbicacionEventoDTO> listasCateringUbicacionEventoDTO;
	
	//Métodos Conversores
	
	//ConvertToDTO
	public static DecoradoDTO convertToDTO(Decorado decorado) {
		
		//Creamos los objetos auxiliares
		DecoradoDTO decoradoDTO = new DecoradoDTO();
		
		//Seteamos los atributos
		decoradoDTO.setId(decorado.getId());
		decoradoDTO.setNombre(decorado.getNombre());
		decoradoDTO.setDescripcion(decorado.getDescripcion());
		decoradoDTO.setFoto(decorado.getFoto());
		decoradoDTO.setPrecio(decorado.getPrecio());
		decoradoDTO.setEmpresaDTO(EmpresaDTO.convertToDTO(decorado.getEmpresa()));
		
		return decoradoDTO;
	}
	
	//ConvertToEntity
	public static Decorado convertToEntity(DecoradoDTO decoradoDTO) {
		
		//Creamos los objetos auxiliares
		Decorado decorado = new Decorado();
		
		//Seteamos los atributos
		decorado.setId(decoradoDTO.getId());
		decorado.setNombre(decoradoDTO.getNombre());
		decorado.setDescripcion(decoradoDTO.getDescripcion());
		decorado.setFoto(decoradoDTO.getFoto());
		decorado.setPrecio(decoradoDTO.getPrecio());
		
		return decorado;
	}
	
	
	//Constructor
	
	public DecoradoDTO() {
		this.empresaDTO = new EmpresaDTO();
		this.listasCateringUbicacionEventoDTO = new ArrayList<CateringUbicacionEventoDTO>();		
	}
}
