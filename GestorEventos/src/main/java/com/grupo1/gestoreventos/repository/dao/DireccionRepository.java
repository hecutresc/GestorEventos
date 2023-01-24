package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo1.gestoreventos.repository.entity.Direccion;

import jakarta.transaction.Transactional;

@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
	
}
