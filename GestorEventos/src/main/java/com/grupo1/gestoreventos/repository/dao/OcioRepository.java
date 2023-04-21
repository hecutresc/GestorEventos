package com.grupo1.gestoreventos.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Catering;
import com.grupo1.gestoreventos.repository.entity.Ocio;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OcioRepository extends JpaRepository<Ocio, Long>{
	
	@Query(value = "SELECT c FROM Ocio c WHERE c.empresa.id = :idEmpresa")
	public List<Ocio> findAllByEmpresa(@Param("idEmpresa") Long idEmpresa);
}
