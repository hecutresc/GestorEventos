package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.DireccionDTO;

public interface DireccionService {

    List<DireccionDTO> findAll();
    DireccionDTO findById(DireccionDTO direccionDTO);
    void save(DireccionDTO direccionDTO);
    void delete(DireccionDTO direccionDTO);

}
