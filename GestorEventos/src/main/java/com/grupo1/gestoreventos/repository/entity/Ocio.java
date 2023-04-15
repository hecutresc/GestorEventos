package com.grupo1.gestoreventos.repository.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "ocio")
public class Ocio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "foto")
	private String foto;
	
	@Column(name = "precio_hora")
	private Float precio_hora;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	@ToString.Exclude
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "catering")
	@ToString.Exclude
	private Set<CateringUbicacionEvento> listasCateringUbicacionEvento;

	//HashCode && Equals
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocio other = (Ocio) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	//Constructor por defecto
	public Ocio() {
		this.empresa = new Empresa();
		this.listasCateringUbicacionEvento = new HashSet<CateringUbicacionEvento>();
	}
	
	
	
	
}
