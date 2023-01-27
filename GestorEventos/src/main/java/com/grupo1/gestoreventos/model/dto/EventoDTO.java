package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.grupo1.gestoreventos.repository.entity.Evento;
import lombok.Data;

@Data
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date fechaInicio;
	private Date fechaFin;
	private String nombre;
	private Date creacion;
	private UbicacionDTO ubicacionDTO;
	private UsuarioDTO usuarioDTO;
	private List<InvitadoDTO> listaInvitadosDTO;
	private List<CateringUbicacionEventoDTO> listaCateringubicacioneventoDTO;

	public static EventoDTO convertToDTO(Evento evento) {
		// Implementar

		return null;
	}

	public static Evento convertToEntity(EventoDTO eventoDTO) {
		// Implementar

		return null;
	}

	public EventoDTO() {
		super();
		this.listaInvitadosDTO = new ArrayList<InvitadoDTO>();
		this.listaCateringubicacioneventoDTO = new ArrayList<CateringUbicacionEventoDTO>();
	}

}
