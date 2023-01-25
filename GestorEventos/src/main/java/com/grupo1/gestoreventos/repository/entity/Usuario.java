package com.grupo1.gestoreventos.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo")
	private int tipo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apeliidos")
	private String apellidos;
	
	@Column(name = "nif")
	private String nif;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@Column(name = "clave_acceso")
	private String claveAcceso;

	@Column(name = "activo")
	private int activo;

	@OneToMany
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;

}
