package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo1.gestoreventos.repository.entity.Usuario;
import jakarta.transaction.Transactional;



@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
}