package com.grupo1.gestoreventos.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
	
}
