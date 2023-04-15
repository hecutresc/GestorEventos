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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "calle")
	private String calle;
    
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "cp")
	private String cp;
	
	@Column(name = "provincia")
	private String provincia;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "direccion")
	private Set<Empresa> listaEmpresas;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "direccion")
	private Set<Ubicacion> listaUbicaciones;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "direccion")
	private Set<Usuario> listaUsuarios;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	} 
	
	public Direccion() {
		super();
		this.listaEmpresas = new HashSet<Empresa>();
		this.listaUbicaciones = new HashSet<Ubicacion>();
		this.listaUsuarios =  new HashSet<Usuario>();
	}
	
}