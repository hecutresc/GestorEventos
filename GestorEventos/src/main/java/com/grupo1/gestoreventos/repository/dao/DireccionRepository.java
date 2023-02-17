package com.grupo1.gestoreventos.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Direccion;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

	@Query(value = "SELECT d FROM Direccion d WHERE id = (SELECT u.direccion.id FROM Usuario u WHERE id = :idUsuario)")
	public Optional<Direccion> findByUsuario(@Param("idUsuario") Long idUsuario);

	@Query(value = "SELECT d FROM Direccion d WHERE id = (SELECT e.direccion.id FROM Empresa e WHERE id = :idEmpresa)")
	public Optional<Direccion> findByEmpresa(@Param("idEmpresa") Long idEmpresa);

	@Query(value = "SELECT d FROM Direccion d WHERE id = (SELECT u.direccion.id FROM Ubicacion u WHERE id = :idUbicacion)")
	public Optional<Direccion> findByUbicacion(@Param("idUbicacion") Long idUbicacion);

}
