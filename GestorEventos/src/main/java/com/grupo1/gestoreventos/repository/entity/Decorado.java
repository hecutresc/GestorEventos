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
@Table(name = "decorados")
public class Decorado {
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "foto")
	private String foto;
	
	@Column(name = "precio")
	private Float precio;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	@ToString.Exclude
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "decorado")
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
		Decorado other = (Decorado) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	//Constructor por defecto de Decorado
	public Decorado() {
		this.empresa = new Empresa();
		this.listasCateringUbicacionEvento = new HashSet<CateringUbicacionEvento>();
	}
	

}
