package com.grupo1.gestoreventos.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Catering;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CateringRepository extends JpaRepository<Catering, Long>{

	@Query(value = "SELECT c FROM Catering c WHERE c.empresa.id = :idEmpresa")
	public List<Catering> findAllByEmpresa(@Param("idEmpresa") Long idEmpresa);
	
}
