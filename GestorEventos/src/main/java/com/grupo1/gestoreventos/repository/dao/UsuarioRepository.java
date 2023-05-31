package com.grupo1.gestoreventos.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Direccion;
import com.grupo1.gestoreventos.repository.entity.Usuario;
import jakarta.transaction.Transactional;



@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByNombreUsuario(String nombreUsuario);
	
	@Query(value = "SELECT u FROM Usuario u WHERE nombreUsuario = :nombreusuario")
	public Optional<Usuario> findByUsername(@Param("nombreusuario") String nombreusuario);
}
