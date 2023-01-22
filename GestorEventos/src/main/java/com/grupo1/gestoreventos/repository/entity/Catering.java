package com.grupo1.gestoreventos.repository.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="caterings")
public class Catering {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "menu")
	private String menu;
	
	@Column(name = "precio")
	private Float precio;
	
	@ManyToOne( fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empresa")
	@ToString.Exclude
	private Empresa empresa;

	// HashCode && Equals
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catering other = (Catering) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	//Constructor
	public Catering() {
		this.empresa = new Empresa();
	}
	
}
