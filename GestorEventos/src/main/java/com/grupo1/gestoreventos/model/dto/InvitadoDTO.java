package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;

import com.grupo1.gestoreventos.repository.entity.Evento;
import com.grupo1.gestoreventos.repository.entity.Invitado;
import lombok.Data;
import lombok.ToString;

@Data
public class InvitadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String token;
	private Integer asistencia;
	
	@ToString.Exclude
	private EventoDTO eventoDTO;

	public static InvitadoDTO convertToDTO(Invitado invitado, EventoDTO eventoDTO) {
		InvitadoDTO invitadoDTO = new InvitadoDTO();
		invitadoDTO.setId(invitado.getId());
		invitadoDTO.setEmail(invitado.getEmail());
		invitadoDTO.setToken(invitado.getToken());
		invitadoDTO.setAsistencia(invitado.getAsistencia());

		invitadoDTO.setEventoDTO(eventoDTO);

		return invitadoDTO;
	}

	public static Invitado convertToEntity(InvitadoDTO invitadoDTO, Evento evento) {
		Invitado invitado = new Invitado();
		invitado.setId(invitadoDTO.getId());
		invitado.setEmail(invitadoDTO.getEmail());
		invitado.setToken(invitadoDTO.getToken());
		invitado.setAsistencia(invitadoDTO.getAsistencia());

		invitado.setEvento(evento);

		return invitado;
	}

}
