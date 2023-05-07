package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.grupo1.gestoreventos.repository.entity.Evento;
import lombok.Data;
import lombok.ToString;

@Data
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaInicio;

	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaFin;

	private String nombre;
	
	private String tipo;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creacion;
	
	private Float precio;

	@ToString.Exclude
	private UbicacionDTO ubicacionDTO;

	@ToString.Exclude
	private UsuarioDTO usuarioDTO;

	@ToString.Exclude
	private List<InvitadoDTO> listaInvitadosDTO;

	@ToString.Exclude
	private List<CateringUbicacionEventoDTO> listaCateringubicacioneventoDTO;
	
	@ToString.Exclude
	private CateringDTO cateringDTO;
	
	@ToString.Exclude
	private DecoradoDTO decoradoDTO;
	
	@ToString.Exclude
	private OcioDTO ocioDTO;
	
	

	public static EventoDTO convertToDTO(Evento evento) {
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(evento.getId());
		eventoDTO.setFechaInicio(evento.getFechaInicio());
		eventoDTO.setFechaFin(evento.getFechaFin());
		eventoDTO.setNombre(evento.getNombre());
		eventoDTO.setTipo(evento.getTipo());
		eventoDTO.setCreacion(evento.getCreacion());
		eventoDTO.setPrecio(evento.getPrecio());
		eventoDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(evento.getUsuario()));

		return eventoDTO;
	}

	public static Evento convertToEntity(EventoDTO eventoDTO) {
		Evento evento = new Evento();
		evento.setId(eventoDTO.getId());
		evento.setFechaInicio(eventoDTO.getFechaInicio());
		evento.setFechaFin(eventoDTO.getFechaFin());
		evento.setNombre(eventoDTO.getNombre());
		evento.setTipo(eventoDTO.getTipo());
		evento.setCreacion(eventoDTO.getCreacion());
		evento.setPrecio(eventoDTO.getPrecio());

		return evento;
	}

	public EventoDTO() {
		super();
		this.listaInvitadosDTO = new ArrayList<InvitadoDTO>();
		this.listaCateringubicacioneventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
		this.ubicacionDTO = new UbicacionDTO();
		this.cateringDTO = new CateringDTO();
		this.ocioDTO = new OcioDTO();
		this.decoradoDTO = new DecoradoDTO();
	}

	public EventoDTO(Long id) {
		super();
		this.id = id;
		this.listaInvitadosDTO = new ArrayList<InvitadoDTO>();
		this.listaCateringubicacioneventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
		this.ubicacionDTO = new UbicacionDTO();
	}

}
