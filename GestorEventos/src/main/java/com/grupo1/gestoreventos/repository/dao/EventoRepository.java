package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.gestoreventos.repository.entity.Evento;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}