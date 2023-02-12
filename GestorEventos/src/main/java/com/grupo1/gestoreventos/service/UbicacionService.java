package com.grupo1.gestoreventos.service;

import java.util.List;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;

public interface UbicacionService {

	public List<UbicacionDTO> findAll();
	public UbicacionDTO findById(UbicacionDTO ubicacionDTO);
	public void save(UbicacionDTO ubicacionDTO);
	public void delete(UbicacionDTO ubicacionDTO);
}
