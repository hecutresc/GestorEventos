package com.grupo1.gestoreventos.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo1.gestoreventos.repository.entity.Decorado;

public interface DecoradoRepository extends JpaRepository<Decorado, Long>{

	@Query(value = "SELECT c FROM Decorado c WHERE c.empresa.id = :idEmpresa")
	public List<Decorado> findAllByEmpresa(@Param("idEmpresa") Long idEmpresa);
}
