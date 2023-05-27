package com.grupo1.gestoreventos.repository.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.type.TrueFalseConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creacion;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name= "precio")
	private Float precio;
	
	@Column(name = "n_asistentes")
	private Long n_asistentes;

	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", nullable = true)
	private Date fechaFin;
	
	@Column(name = "hora_inicio", nullable = true)
	private String hora_incio;
	
	@Column(name = "num_horas", nullable = true)
	private Long num_horas;

	@ManyToOne
	@JoinColumn(name = "id_ubicacion")
	@ToString.Exclude
	private Ubicacion ubicacion;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@ToString.Exclude
	private Usuario usuario;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "evento")
	@ToString.Exclude
	private Set<Invitado> listaInvitados;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "evento")
	@ToString.Exclude
	private Set<CateringUbicacionEvento> listaCateringUbicacionEvento;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Evento() {
		super();
		this.listaInvitados = new HashSet<Invitado>();
		this.listaCateringUbicacionEvento = new HashSet<CateringUbicacionEvento>();
	}

}
