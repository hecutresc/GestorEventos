package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.grupo1.gestoreventos.repository.entity.Evento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "¡La fecha de inicio no puede ser nula!")
    @Future(message = "¡La fecha de inicio debe ser posterior a la fecha actual!")
	private Date fechaInicio;

	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaFin;
	
	private String hora_inicio;
	
	private Long num_horas;

	@NotEmpty(message = "¡El nombre del evento no puede estar vacío!")
	private String nombre;
	
	@NotEmpty(message = "¡Tienes que elegir un tipo de evento!")
	@Pattern(regexp = "^(?!0$).*", message = "¡Tienes que elegir un tipo de Evento!")
	private String tipo;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creacion;
	
	private Float precio;
	
	private Long n_asistentes;

	@ToString.Exclude
    @NotNull(message = "¡La ubicación no puede ser nula!")
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
		eventoDTO.setHora_inicio(evento.getHora_incio());
		eventoDTO.setNum_horas(evento.getNum_horas());
		eventoDTO.setNombre(evento.getNombre());
		eventoDTO.setTipo(evento.getTipo());
		eventoDTO.setCreacion(evento.getCreacion());
		eventoDTO.setPrecio(evento.getPrecio());
		eventoDTO.setN_asistentes(evento.getN_asistentes());
		eventoDTO.setUbicacionDTO(UbicacionDTO.convertToDTO(evento.getUbicacion()));
		eventoDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(evento.getUsuario()));

		return eventoDTO;
	}

	public static Evento convertToEntity(EventoDTO eventoDTO) {
		Evento evento = new Evento();
		evento.setId(eventoDTO.getId());
		evento.setFechaInicio(eventoDTO.getFechaInicio());
		evento.setFechaFin(eventoDTO.getFechaFin());
		evento.setHora_incio(eventoDTO.getHora_inicio());
		evento.setNum_horas(eventoDTO.getNum_horas());
		evento.setNombre(eventoDTO.getNombre());
		evento.setTipo(eventoDTO.getTipo());
		evento.setCreacion(eventoDTO.getCreacion());
		evento.setPrecio(eventoDTO.getPrecio());
		evento.setN_asistentes(eventoDTO.getN_asistentes());
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
