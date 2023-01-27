package com.grupo1.gestoreventos.model.dto;

import java.io.Serializable;
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

	public static InvitadoDTO convertToDTO(Invitado invitado) {
		// Implementar
		return null;
	}

	public static Invitado convertToEntity(InvitadoDTO invitadoDTO) {
		// Implementar
		return null;
	}

}
