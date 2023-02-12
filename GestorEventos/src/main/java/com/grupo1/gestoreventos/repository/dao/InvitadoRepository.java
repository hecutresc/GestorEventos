package com.grupo1.gestoreventos.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.grupo1.gestoreventos.repository.entity.Invitado;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface InvitadoRepository extends JpaRepository<Invitado, Long> {

	@Query(value="SELECT i FROM Invitado i WHERE i.evento.id = :idEvento")
	public List<Invitado> findAllByEvento(@Param("idEvento") Long idEvento);

}