package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.DireccionDTO;

public interface DireccionService {

    public List<DireccionDTO> findAll();
    public DireccionDTO findById(DireccionDTO direccionDTO);
    public void save(DireccionDTO direccionDTO);
    public void delete(DireccionDTO direccionDTO);

}
