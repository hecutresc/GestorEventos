package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Catering;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CateringRepository extends JpaRepository<Catering, Long>{

}
