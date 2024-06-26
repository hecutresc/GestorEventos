package com.grupo1.gestoreventos.model.dto;

import java.util.Date;

import com.grupo1.gestoreventos.repository.entity.CateringUbicacionEvento;

import lombok.Data;
import lombok.ToString;

@Data

public class CateringUbicacionEventoDTO {

	//Atributos
	private Long id;
	
	private Date fechahora;
	
	@ToString.Exclude
	private CateringDTO cateringDTO;
	
	@ToString.Exclude
	private OcioDTO ocioDTO;
	
	@ToString.Exclude
	private DecoradoDTO decoradoDTO;
	
	@ToString.Exclude
	private UbicacionDTO ubicacionDTO;
	
	@ToString.Exclude
	private EventoDTO eventoDTO;
	
	//Métodos Convertores
	
	//ConvertToDTO
	public static CateringUbicacionEventoDTO convertToDTO(CateringUbicacionEvento cateringUbicacionEvento) {
		//Creación de Objetos
		CateringUbicacionEventoDTO cateringUbicacionEventoDTO = new CateringUbicacionEventoDTO();
		
		//Set de los Atributos
		cateringUbicacionEventoDTO.setId(cateringUbicacionEvento.getId());
		cateringUbicacionEventoDTO.setFechahora(cateringUbicacionEvento.getFechahora());
		if(cateringUbicacionEvento.getCatering() != null) {
			cateringUbicacionEventoDTO.setCateringDTO(CateringDTO.convertToDTO(cateringUbicacionEvento.getCatering()));
		}else {
			cateringUbicacionEventoDTO.setCateringDTO(new CateringDTO());
		}
		
		if(cateringUbicacionEvento.getOcio() != null) {
			cateringUbicacionEventoDTO.setOcioDTO(OcioDTO.convertToDTO(cateringUbicacionEvento.getOcio()));
		}else {
			cateringUbicacionEventoDTO.setOcioDTO(new OcioDTO());
		}
		
		if(cateringUbicacionEvento.getDecorado() != null) {
			cateringUbicacionEventoDTO.setDecoradoDTO(DecoradoDTO.convertToDTO(cateringUbicacionEvento.getDecorado()));
		}else {
			cateringUbicacionEventoDTO.setDecoradoDTO(new DecoradoDTO());
		}
		
		
		cateringUbicacionEventoDTO.setUbicacionDTO(UbicacionDTO.convertToDTO(cateringUbicacionEvento.getUbicacion()));
		cateringUbicacionEventoDTO.setEventoDTO(EventoDTO.convertToDTO(cateringUbicacionEvento.getEvento()));
		
		return cateringUbicacionEventoDTO;
	}
	
	//ConvertToEntity
	public static CateringUbicacionEvento convertToEntity(CateringUbicacionEventoDTO cateringUbicacionEventoDTO) {
		//Creación de Objetos
		CateringUbicacionEvento cateringUbicacionEvento = new CateringUbicacionEvento();
		
		//Set de los Atributos
		cateringUbicacionEvento.setId(cateringUbicacionEventoDTO.getId());
		cateringUbicacionEvento.setFechahora(cateringUbicacionEventoDTO.getFechahora());
		cateringUbicacionEvento.setCatering(CateringDTO.convertToEntity(cateringUbicacionEventoDTO.getCateringDTO()));
		cateringUbicacionEvento.setOcio(OcioDTO.convertToEntity(cateringUbicacionEventoDTO.getOcioDTO()));
		cateringUbicacionEvento.setDecorado(DecoradoDTO.convertToEntity(cateringUbicacionEventoDTO.getDecoradoDTO()));
		cateringUbicacionEvento.setUbicacion(UbicacionDTO.convertToEntity(cateringUbicacionEventoDTO.getUbicacionDTO()));
		cateringUbicacionEvento.setEvento(EventoDTO.convertToEntity(cateringUbicacionEventoDTO.getEventoDTO()));
		
		return cateringUbicacionEvento;
	}
	
	
	//Constructor
	public CateringUbicacionEventoDTO () {
		this.cateringDTO = new CateringDTO();
		this.ocioDTO = new OcioDTO();
		this.decoradoDTO = new DecoradoDTO();
		this.ubicacionDTO = new UbicacionDTO();
		this.eventoDTO = new EventoDTO();
	}
	
}
