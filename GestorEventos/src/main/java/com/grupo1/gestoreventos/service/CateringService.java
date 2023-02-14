package com.grupo1.gestoreventos.service;

import java.util.List;
import com.grupo1.gestoreventos.model.dto.CateringDTO;

public interface CateringService {

	public List<CateringDTO> findAll();
	public CateringDTO findById(CateringDTO cateringDTO);
	public void save(CateringDTO cateringDTO);
	public void delete(CateringDTO cateringDTO);
}
