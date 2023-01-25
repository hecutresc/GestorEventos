package com.grupo1.gestoreventos.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
=======

>>>>>>> refs/heads/ubicacioncateringempresa
import com.grupo1.gestoreventos.repository.entity.Direccion;
import jakarta.transaction.Transactional;




@Transactional
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
	
}
