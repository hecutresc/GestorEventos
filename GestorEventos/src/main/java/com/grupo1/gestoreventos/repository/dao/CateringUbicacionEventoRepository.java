package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.CateringUbicacionEvento;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CateringUbicacionEventoRepository extends JpaRepository<CateringUbicacionEvento, Long> {

	@Query(value = "SELECT cue FROM CateringUbicacionEvento cue WHERE cue.catering.id = :idCatering AND cue.ubicacion.id = :idUbicacion AND cue.evento.id = :idEvento")
	CateringUbicacionEvento findByCUE(@Param("idEvento") Long idEvento, @Param("idUbicacion") Long idUbicacion,
			@Param("idCatering") Long idCatering);

}
