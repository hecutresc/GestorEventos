package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.EmpresaDTO;

public interface EmpresaService {

	public List<EmpresaDTO> findAll();
	public EmpresaDTO findById(EmpresaDTO empresaDTO);
	public void save(EmpresaDTO empresaDTO);
	public void delete(EmpresaDTO empresaDTO);
}
