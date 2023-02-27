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

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creacion;

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

	public static EventoDTO convertToDTO(Evento evento) {
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(evento.getId());
		eventoDTO.setFechaInicio(evento.getFechaInicio());
		eventoDTO.setFechaFin(evento.getFechaFin());
		eventoDTO.setNombre(evento.getNombre());
		eventoDTO.setCreacion(evento.getCreacion());
		eventoDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(evento.getUsuario()));

		return eventoDTO;
	}

	public static Evento convertToEntity(EventoDTO eventoDTO) {
		Evento evento = new Evento();
		evento.setId(eventoDTO.getId());
		evento.setFechaInicio(eventoDTO.getFechaInicio());
		evento.setFechaFin(eventoDTO.getFechaFin());
		evento.setNombre(eventoDTO.getNombre());
		evento.setCreacion(eventoDTO.getCreacion());

		return evento;
	}

	public EventoDTO() {
		super();
		this.listaInvitadosDTO = new ArrayList<InvitadoDTO>();
		this.listaCateringubicacioneventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
		this.ubicacionDTO = new UbicacionDTO();
	}

	public EventoDTO(Long id) {
		super();
		this.id = id;
		this.listaInvitadosDTO = new ArrayList<InvitadoDTO>();
		this.listaCateringubicacioneventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
		this.ubicacionDTO = new UbicacionDTO();
	}

}
