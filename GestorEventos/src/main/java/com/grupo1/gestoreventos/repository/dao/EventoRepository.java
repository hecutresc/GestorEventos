package com.grupo1.gestoreventos.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Evento;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	@Query(value = "select * from eventos where id_usuario = :idu", nativeQuery = true)
	List<Evento> findAllByUsuario(@Param("idu") Long idUsuario);

	@Query(value= "SELECT e FROM Evento e WHERE e.usuario.id = :idUsuario")
	public List<Evento> findAllByUser(@Param("idUsuario") Long idUsuario);
	
	@Modifying
	@Query(value="UPDATE Evento e SET e.pagado = :p WHERE e.id = :id")
	void pagar(@Param("id") Long id, @Param("p") Long p);

}