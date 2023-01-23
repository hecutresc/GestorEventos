package com.grupo1.gestoreventos.repository.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.repository.entity.Cuenta;

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
@Table(name="ubicaciones")
public class Ubicacion {

	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "aforo")
	private String aforo;
	
	@ManyToOne
	@JoinColumn(name = "id_direccion")
	@ToString.Exclude
	private Direccion direccion;
	
	@OneToMany( fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			mappedBy = "ubicacion")
	private Set<Cateringubicacionevento> listaCateringubicacioneventos;
	
	
	
	//Hash id object equals
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ubicacion other = (Ubicacion) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public Ubicacion() {
		this.direccion = new Direccion();
		this.listaCateringubicacioneventos = new HashSet<Cateringubicacionevento>();
	}
}
